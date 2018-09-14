package com.kute.demo.datasource;

import com.google.common.base.Strings;
import com.kute.demo.exception.DataSourceException;

/**
 * created by bailong001 on 2018/09/13 21:13
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> routeKey = new ThreadLocal<>();

    public enum DataSourceType {
        MASTER("master"),
        SLAVE("slave");

        public final String name;

        DataSourceType(String name) {
            this.name = name;
        }
    }

    /**
     * 设置 为主库
     */
    public static void setMasterDataSource() {
        routeKey.set(DataSourceType.MASTER.name);
    }

    /**
     * 从库
     */
    public static void setSlaveDataSource() {
        routeKey.set(DataSourceType.SLAVE.name);
    }

    public static void setDataSource(String dataSource) {
        if(Strings.isNullOrEmpty(dataSource)) {
            throw new DataSourceException("DataSource set illegal value key:" + dataSource);
        }
        if(DataSourceType.MASTER.name.equalsIgnoreCase(dataSource)) {
            setMasterDataSource();
            return;
        }
        if(DataSourceType.SLAVE.name.equalsIgnoreCase(dataSource)) {
            setSlaveDataSource();
            return;
        }
        throw new DataSourceException("DataSource set illegal value key:" + dataSource);
    }

    public static String getDataSource() {
        String dataSource = routeKey.get();
        return Strings.isNullOrEmpty(dataSource) ? DataSourceType.MASTER.name : dataSource;
    }

    public static void clear() {
        routeKey.remove();
    }

}
