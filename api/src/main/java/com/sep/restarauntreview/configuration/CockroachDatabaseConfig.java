package com.sep.restarauntreview.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CockroachDatabaseConfig {

    @Bean
    NamedParameterJdbcOperations operations(Environment environment) {
        return new NamedParameterJdbcTemplate(dataSource(environment));
    }

    @Bean
    PlatformTransactionManager transactionManager(Environment environment) {
        return new DataSourceTransactionManager(dataSource(environment));
    }

    @Bean
    DataSource dataSource(Environment environment) {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(environment.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(environment.getRequiredProperty("db.username"));
        dataSourceConfig.setPassword(environment.getRequiredProperty("db.password"));
        return new HikariDataSource(dataSourceConfig);
    }
}
