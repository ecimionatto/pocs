package com.web.poc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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
	PagesRepository pagesRepository;

	private void cleanupTables() {
		Query nativeQuery = pagesRepository.entityManager
				.createNativeQuery("delete from layout_temp");
		nativeQuery.executeUpdate();

		nativeQuery = pagesRepository.entityManager
				.createNativeQuery("delete from pages_temp where parent = null");
		nativeQuery.executeUpdate();

		nativeQuery = pagesRepository.entityManager
				.createNativeQuery("delete from pages_temp");
		nativeQuery.executeUpdate();

		pagesRepository.entityManager.flush();
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
		System.out.println("started!");

		cleanupTables();
		System.out.println("cleanup done!");

		int records = 100000;

		insertRecords(records);
		System.out.println("inserts done!");

		List<Page> pages = selectAll();

		updateRecords(pages, records);
		System.out.println("updates done!");

		pages = selectAll();

		pagesRepository.bulkRemove(pages);
		System.out.println("removes done!");
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
