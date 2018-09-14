package com.kute.demo.aop;

import com.google.common.base.Strings;
import com.kute.demo.annotation.DataSource;
import com.kute.demo.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * created by bailong001 on 2018/09/14 10:16
 */
@Aspect
@Component
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    private final String EXPRESSION = "@annotation(com.kute.demo.annotation.DataSource)";

    @Around(EXPRESSION)
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, DataSource dataSource) throws Throwable {
        try {
            if (null != dataSource && !Strings.isNullOrEmpty(dataSource.value())) {
                LOGGER.info("Set Current DataSource[{}]", dataSource.value());
                DynamicDataSourceHolder.setDataSource(dataSource.value());
            }
            return proceedingJoinPoint.proceed();
        } finally {
            DynamicDataSourceHolder.clear();
            LOGGER.info("Restore DataSource");
        }
    }

}
