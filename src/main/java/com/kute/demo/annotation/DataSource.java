package com.kute.demo.annotation;

import com.kute.demo.datasource.route.DynamicDataSourceHolder;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 数据源key
     * @see DynamicDataSourceHolder.DataSourceType
     * @return
     */
    String value() default "master";

}
