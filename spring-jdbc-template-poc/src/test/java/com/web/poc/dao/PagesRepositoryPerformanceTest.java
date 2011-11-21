package com.web.poc.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.web.poc.entity.Layout;
import com.web.poc.entity.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-oracle-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class PagesRepositoryPerformanceTest {

	@Autowired
	PageRepository pagesRepository;

	private void cleanupTables() {
		pagesRepository.jdbcTemplate.execute("delete from layout_temp");
		pagesRepository.jdbcTemplate
				.execute("delete from pages_temp where parent = null");
		pagesRepository.jdbcTemplate.execute("delete from pages_temp");
	}

	private void insertRecords(int records) {
		Page parent = null;
		List<Page> pages = new ArrayList<Page>();
		for (long i = 0; i < records; i++) {
			Page page = new Page();
			page.setId(i);
			page.setName("page inserted " + i);
			Layout layout = new Layout();
			layout.setId(i);
			layout.setName("layout inserted " + i);
			page.setLayout(layout);
			if (parent != null) {
				page.setParent(parent);
			}
			parent = page;
			pages.add(page);
		}
		pagesRepository.bulkPersist(pages);
	}

	@Test
	public void peformanceTest() {

		cleanupTables();
		System.out.println("cleanup done!");

		int records = 100000;
		insertRecords(records);
		System.out.println("insert done!");

		List<Page> pages = selectAll();

		updateRecords(pages, records);
		System.out.println("update done!");

		pages = selectAll();

		pagesRepository.bulkRemove(pages);
		System.out.println("delete done!");

	}

	private List<Page> selectAll() {
		List<Page> pages;
		pages = pagesRepository.findAll();
		for (Page page : pages) {
			System.out.println("name: " + page.getName());
		}
		return pages;
	}

	private void updateRecords(List<Page> pages, int records) {
		for (Page page : pages) {
			page.setName("page updated " + page.getId());
		}
		pagesRepository.bulkUpdate(pages);
	}

}
