package com.kute.demo.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 数据源key
     * @see com.kute.demo.datasource.DynamicDataSourceHolder.DataSourceType
     * @return
     */
    String value() default "master";

}
