package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-05 20:40
 * 实现BeanFactoryAware接口的Bean，在Bean加载的过程中可以获取到加载该Bean的BeanFactory
 */
@Component
public class BeanFactoryAwareTester implements BeanFactoryAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanFactoryAwareTester.class);

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOGGER.debug("spring interface setBeanFactory beanFactory:{}", beanFactory.getClass().getSimpleName());
    }
}
