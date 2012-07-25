package com.opeak.poc.repository;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class RepositoryIntegrationTest {

	@Autowired
	private DataSource dataSource;

	@org.junit.Test
	public void testDatabaseConnection() {
		assertNotNull(dataSource);
	}

}