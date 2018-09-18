package com.kute.demo.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceInitializedEvent;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018/08/06 20:13
 * <p>
 * 监听 数据源  初始化事件
 * <p>
 * prefix:  spring.datasource
 */
@Component
public class DataSourceInitializerEventListener implements ApplicationListener<DataSourceInitializedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceInitializerEventListener.class);

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * 配置  spring.datasource 前缀的数据源
     */
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Override
    public void onApplicationEvent(DataSourceInitializedEvent event) {

        logger.info("DataSourceInitializedEvent [spirng.datasource] DataSourceProperties isInitialize={}", dataSourceProperties.isInitialize());


    }
}
