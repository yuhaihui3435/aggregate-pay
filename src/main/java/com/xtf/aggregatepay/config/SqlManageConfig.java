
package com.xtf.aggregatepay.config;

import com.xtf.aggregatepay.util.DelSuffixConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlManageConfig {
    @Bean
    public SqlManagerFactoryBean sqlManagerFactoryBean(@Qualifier("beetlSqlDataSource") BeetlSqlDataSource beetlSqlDataSource){
        SqlManagerFactoryBean sqlManagerFactoryBean=new SqlManagerFactoryBean();
        sqlManagerFactoryBean.setCs(beetlSqlDataSource);
        sqlManagerFactoryBean.setDbStyle(new MySqlStyle());
        sqlManagerFactoryBean.setNc(new DelSuffixConversion());
//        sqlManagerFactoryBean.setInterceptors(new Interceptor[]{
//                new DebugInterceptor()
//        });
        return sqlManagerFactoryBean;
    }

    @Bean(name = "beetlSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage("com.xtf.aggregatepay.dao");
        conf.setDaoSuffix("Dao");
        conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
        return conf;
    }
}
