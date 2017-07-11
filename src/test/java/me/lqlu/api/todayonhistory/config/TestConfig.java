package me.lqlu.api.todayonhistory.config;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.lqlu.AbstractSpringTest4Junit;

public class TestConfig extends AbstractSpringTest4Junit {

	@Autowired
	private DataSource ds;

	@Test
	public void testConfig() {
		Assert.assertNotNull(ds);
	}

}
