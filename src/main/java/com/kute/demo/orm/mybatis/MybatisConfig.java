package com.kute.demo.orm.mybatis;

import com.kute.demo.orm.mybatis.plugin.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * created by kute on 2018/09/18 19:48
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = {
        "com.kute.demo.orm.mybatis.mapper"
})
public class MybatisConfig {

    @Resource(name = "roundRobinDataSouceProxy")
    private AbstractRoutingDataSource roundRobinDataSouceProxy;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver
                .getResources("classpath*:com/kute/demo/orm/mybatis/mapper/**/*.xml"));

        //设置分页插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new PageInterceptor()});
        return sqlSessionFactoryBean.getObject();
    }

}
