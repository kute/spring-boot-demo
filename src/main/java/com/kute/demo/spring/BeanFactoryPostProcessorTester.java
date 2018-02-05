package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-05 20:18
 * Bean factory 预处理
 */
@Component
public class BeanFactoryPostProcessorTester implements BeanFactoryPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanFactoryPostProcessorTester.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        LOGGER.debug("spring interface postProcessBeanFactory factory type:{}", configurableListableBeanFactory.getClass().getSimpleName());
    }
}
