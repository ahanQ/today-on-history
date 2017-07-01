package me.lqlu;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import me.lqlu.spring.config.Config;

@ContextConfiguration(classes = { Config.class })
public class AbstractSpringTest4Junit extends AbstractJUnit4SpringContextTests {

	@Test
	public void initTest() {

	}

}
