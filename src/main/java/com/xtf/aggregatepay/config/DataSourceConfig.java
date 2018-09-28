package com.xtf.aggregatepay.config;

import com.zaxxer.hikari.HikariDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name="dataSource")
    public DataSource datasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;

//        DruidDataSource druidDataSource=new DruidDataSource();
//        druidDataSource.setName("APDS");
//        druidDataSource.setUrl(env.getProperty("spring.datasource.url"));
//        druidDataSource.setUsername(env.getProperty("spring.datasource.username"));
//        druidDataSource.setPassword(env.getProperty("spring.datasource.password"));
//
//        //druidDataSource配置
//        druidDataSource.setMaxActive(Integer.parseInt(env.getProperty("datasource.maxActive")));
//        druidDataSource.setInitialSize(Integer.parseInt(env.getProperty("datasource.initialSize")));
//        druidDataSource.setMaxWait(Long.parseLong(env.getProperty("datasource.maxWaitMillis")));
//        druidDataSource.setMinIdle(Integer.parseInt(env.getProperty("datasource.minIdle")));
//        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("datasource.timeBetweenEvictionRunsMillis")));
//        druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("datasource.minEvictableIdleTimeMillis")));
//        druidDataSource.setValidationQuery(env.getProperty("datasource.validationQuery"));
//        druidDataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("datasource.testWhileIdle")));
//        druidDataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("datasource.testOnBorrow")));
//        druidDataSource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("datasource.testOnReturn")));
//        return druidDataSource;
    }

    @Bean
    public BeetlSqlDataSource beetlSqlDataSource(@Qualifier("dataSource")  DataSource dataSource){
        BeetlSqlDataSource source = new BeetlSqlDataSource();
        source.setMasterSource(dataSource);
        return source;
    }
}
