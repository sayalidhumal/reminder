package com.reminder.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("oracle.jdbc.driver.OracleDriver");
		dataSourceBuilder.url("jdbc:oracle:thin:@localhost:1521:ORACLE");
		dataSourceBuilder.username("SYSTEM");
		dataSourceBuilder.password("admin");
		return dataSourceBuilder.build();
	}
}
