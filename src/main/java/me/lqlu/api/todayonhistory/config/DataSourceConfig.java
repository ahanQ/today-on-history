package me.lqlu.api.todayonhistory.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource dataSource() {
		String url = "jdbc:mysql://localhost:3306/today_on_history?useUnicode=true&characterEncoding=utf-8";
		String username = "root";
		String password = "123456";
		String driverClassName = "com.mysql.jdbc.Driver";
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean
	public JdbcOperations jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
}
