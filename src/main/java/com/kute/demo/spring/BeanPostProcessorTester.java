package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-05 20:01
 * Bean 预处理
 */
@Component
public class BeanPostProcessorTester implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanPostProcessorTester.class);

    /**
     * 在受管bean的初始化动作之前调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.debug("spring interface postProcessBeforeInitialization bean={}, beanName={}", bean, beanName);
        return bean;
    }

    /**
     * 在受管bean的初始化动作之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.debug("spring interface postProcessAfterInitialization bean={}, beanName={}", bean.getClass().getSimpleName(), beanName);
        return bean;
    }
}
