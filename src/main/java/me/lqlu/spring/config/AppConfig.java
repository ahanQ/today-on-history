package me.lqlu.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * spring 配置类
 * 扫描所有子包
 * @author ahan
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "me.lqlu.**" })
public class AppConfig {

}
