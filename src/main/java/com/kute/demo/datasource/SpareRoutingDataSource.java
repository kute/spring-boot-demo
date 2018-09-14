package com.kute.demo.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * created by bailong001 on 2018/09/13 21:11
 * 动态数据源切换
 */
public class SpareRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
