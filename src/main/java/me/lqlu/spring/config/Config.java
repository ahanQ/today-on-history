package me.lqlu.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring 配置类
 * 扫描所有子包
 * @author ahan
 *
 */
@Configuration
@ComponentScan(basePackages = { "me.lqlu.**" })
public class Config {

}
