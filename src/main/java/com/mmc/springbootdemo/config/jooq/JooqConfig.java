package com.mmc.springbootdemo.config.jooq;

import com.alibaba.druid.pool.DruidDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

// 可以不配置，参考连接https://segmentfault.com/a/1190000006748584
// 不配置也不需要在service内多次使用DSL.using(configuration)
@Configuration
public class JooqConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DSLContext getDSLContext() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(20_000);
        dataSource.setMinIdle(0);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setInitialSize(1);
        dataSource.setMinEvictableIdleTimeMillis(1000*60*10);
        dataSource.setTimeBetweenEvictionRunsMillis(60*1000);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
        DataSourceTransactionManager txMgr =  new DataSourceTransactionManager(dataSource);
        org.jooq.Configuration configuration = new DefaultConfiguration()
                .set(new DataSourceConnectionProvider(proxy))
                .set(new MySpringTransactionProvider(txMgr))
                .set(SQLDialect.POSTGRES);

        return DSL.using(configuration);
    }
}
