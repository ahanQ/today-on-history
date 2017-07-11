package me.lqlu;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import me.lqlu.spring.config.AppConfig;

/**
 * 配置好的抽象测试类
 * @author ahan
 *
 */
@ContextConfiguration(classes = { AppConfig.class })
public abstract class AbstractSpringTest4Junit extends AbstractJUnit4SpringContextTests {

}
