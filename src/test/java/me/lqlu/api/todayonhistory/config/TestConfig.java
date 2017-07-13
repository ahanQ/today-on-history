package me.lqlu.api.todayonhistory.config;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.PlatformTransactionManager;

import me.lqlu.AbstractSpringTest4Junit;

public class TestConfig extends AbstractSpringTest4Junit {

	@Autowired
	private DataSource ds;

	@Autowired
	private JdbcOperations jdbc;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Test
	public void testConfig() {
		Assert.assertNotNull(ds);
		Assert.assertNotNull(jdbc);
		Assert.assertNotNull(transactionManager);
	}

}
