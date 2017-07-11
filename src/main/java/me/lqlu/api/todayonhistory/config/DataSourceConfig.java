package me.lqlu.api.todayonhistory.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource dataSource() {
		String url = "jdbc:mysql://localhost:3306/today_on_history?useUnicode=true&characterEncoding=utf-8";
		String username = "root";
		String password = "123456";
		String DriverClassName = "com.mysql.jdbc.Driver";
		DriverManagerDataSource ds = new DriverManagerDataSource(url, username, password);
		ds.setDriverClassName(DriverClassName);
		return ds;
	}

	@Bean
	public JdbcOperations jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
