package com.xtf.aggregatepay.config;

import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name="datasource")
    public DataSource datasource(Environment env) {

        return DataSourceBuilder.create().build();
    }

    @Bean
    public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("datasource")  DataSource dataSource){
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        return source;
    }
}
