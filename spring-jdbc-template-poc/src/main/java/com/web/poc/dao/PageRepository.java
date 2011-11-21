package com.web.poc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.web.poc.entity.Layout;
import com.web.poc.entity.Page;

@Component
public class PageRepository {

	public final static String COLUMNS = "ID, NAME, LAYOUT_ID, PARENT, IMAGE_ID, SEQ_NO, PROPERTIES, LAST_FILENAMECHANGE_DATE, LAST_PUBLISH_DATE, FILENAME, FILENAME_ALIAS, HIDDEN";
	public final static String PK = "ID";

	JdbcTemplate jdbcTemplate;

	public void bulkPersist(final List<Page> pages) {
		final int batchSize = pages.size();

		jdbcTemplate.batchUpdate(
				"insert into layout_temp(id, name) values (?, ?)",
				new BatchPreparedStatementSetter() {
					public int getBatchSize() {
						return batchSize;
					}

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Page page = pages.get(i);
						Layout layout = page.getLayout();
						ps.setLong(1, layout.getId());
						ps.setString(2, layout.getName());
					}
				});

		jdbcTemplate
				.batchUpdate(
						"insert into pages_temp(id, name, parent, layout_id) values (?, ?, ?, ?)",
						new BatchPreparedStatementSetter() {
							public int getBatchSize() {
								return batchSize;
							}

							public void setValues(PreparedStatement ps, int i)
									throws SQLException {
								Page page = pages.get(i);
								ps.setLong(1, page.getId());
								ps.setString(2, page.getName());
								if (page.getParent() != null
										&& page.getParent().getId() != null) {
									ps.setLong(3, page.getParent().getId());
								} else {
									ps.setNull(3, i);
								}
								if (page.getLayout().getId() != null) {
									ps.setLong(4, page.getLayout().getId());
								}
							}
						});

	}

	public void bulkRemove(final List<Page> pages) {
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return pages.size();
			}

			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Page page = pages.get(i);
				ps.setLong(1, page.getId());
			}
		};
		jdbcTemplate.batchUpdate("delete from pages_temp where id = ?", setter);

	}

	public void bulkUpdate(final List<Page> pages) {
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public int getBatchSize() {
				return pages.size();
			}

			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Page page = pages.get(i);
				ps.setString(1, page.getName());
				ps.setLong(2, page.getId());
			}
		};

		jdbcTemplate.batchUpdate("update pages_temp set name = ? where id = ?",
				setter);

	}

	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Page> findAll() {
		RowMapper<Page> rowMapper = initRowMapper();
		List<Page> pages = jdbcTemplate.query("select " + COLUMNS
				+ " from PAGES_TEMP", rowMapper);
		return pages;
	}

	public List<Page> findByParent(String parentName) {
		RowMapper<Page> rowMapper = initRowMapper();
		List<Page> pages = jdbcTemplate
				.query("select a.ID, a.NAME, a.PROPERTIES"
						+ " from PAGES_TEMP a, PAGES b where a.PARENT = b.PARENT and b.NAME= '"
						+ parentName + "'", rowMapper);
		return pages;
	}

	private RowMapper<Page> initRowMapper() {
		RowMapper<Page> rowMapper = new RowMapper<Page>() {
			public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
				Page page = new Page();
				page.setId(rs.getLong("id"));
				page.setName(rs.getString("name"));
				byte[] bytes = rs.getBytes("properties");
				if (bytes != null) {
					page.setProperties(new String(bytes));
				}
				return page;
			}
		};
		return rowMapper;
	}
}
