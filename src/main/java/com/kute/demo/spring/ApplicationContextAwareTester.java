package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-05 20:21
 * 获取ApplicationContext实例
 */
@Component
public class ApplicationContextAwareTester implements ApplicationContextAware, BeanNameAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextAwareTester.class);

    private String beanName;
    private ApplicationContext applicationContext;

    /**
     * BeanNameAware
     * 当bean需要获取自身在容器中的id/name
     * @param beanName
     */
    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        logger.debug("spring interface setBeanName:{}", beanName);
    }

    /**
     * ApplicationContextAware
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        logger.debug("spring interface setApplicationContext:{}", applicationContext.getApplicationName());
    }
}
