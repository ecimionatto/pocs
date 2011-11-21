package com.web.poc.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.web.poc.entity.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-oracle-context.xml" })
@Transactional(readOnly = true)
public class PageDaoTest {
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	PageRepository pageDao;

	@Test
	public void findByParent() {
		List<Page> pages = pageDao.findByParent("location");
		for (Page page : pages) {
			if (page.getProperties() != null) {
				System.out.println(page.getProperties());
			}
		}
	}

	@Test
	public void shouldGetSpringContext() {
		assertNotNull(applicationContext);
		for (String beanName : applicationContext.getBeanDefinitionNames()) {
			System.out.println(beanName + " is part of spring context");
		}
	}

	@Test
	public void shouldSelectAll() {
		List<Page> pages = pageDao.findAll();
		for (Page page : pages) {
			System.out.println("name: " + page.getName());
			if (page.getProperties() != null) {
				System.out.println("prop: " + page.getProperties());
			}
		}
	}
}
