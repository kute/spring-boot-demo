package com.kute.demo.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html#howto-configure-a-datasource
 * Created by kute on 2018/1/11.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceProvider {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceProvider.class);

    /**
     * 动态切换数据源
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy() {
        SpareRoutingDataSource proxy = new SpareRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //主 写
        targetDataSources.put(DynamicDataSourceHolder.DataSourceType.MASTER.name, masterDataSource());
        //从 读
        targetDataSources.put(DynamicDataSourceHolder.DataSourceType.SLAVE.name, slaveDataSource());
        proxy.setDefaultTargetDataSource(masterDataSource());
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterJdbcTemplate")
    @Primary // 使用 主 数据源
    public JdbcTemplate masterJdbcTemplate() throws SQLException{
        DataSource dataSource = masterDataSource();
        logger.info("master jdbctemplate datasource url:{}", dataSource.getConnection().getMetaData().getURL());
        return new JdbcTemplate(dataSource);
    }

    /**
     * 返回标准的datasource
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary // 标示 为 主 数据源
    @ConfigurationProperties(prefix = "mysql.datasource.master")
    public DataSource masterDataSource() {
        // standard datasource
        return DataSourceBuilder.create().build();
    }

    /*

    第二种写法

    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource.master2")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource.master2")
    public DataSource masterDataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    */

//    /*

    @Bean(name = "slaveJdbcTemplate")
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("slaveDataSource") DataSource dataSource) throws SQLException {
        logger.info("slave jdbctemplate datasource url:{}", dataSource.getConnection().getMetaData().getURL());
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.slave")
    public DataSource slaveDataSource() {
        // standard datasource
        return DataSourceBuilder.create().build();
    }

//    */

    /**
     * 因连接池key不一定一样,比如hikari使用jdbc-url而不是url
     * 所以最好返回指定的数据源
     * @return
     */
    /*@Bean
    @ConfigurationProperties(prefix = "hikari.datasource")
    public HikariDataSource hikariDataSource() {
        return (HikariDataSource) DataSourceBuilder.create().type(HikariDataSource.class).build();
    }*/

    /**
     * jndi datasource
     */
    /*@Bean(destroyMethod="")
    @ConfigurationProperties(prefix="app.datasource")
    public DataSource dataSource() throws Exception {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource("java:comp/env/jdbc/YourDS");
    }*/

}
