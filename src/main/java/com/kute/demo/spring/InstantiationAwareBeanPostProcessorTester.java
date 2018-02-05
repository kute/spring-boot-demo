package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * created by kute on 2018-02-05 20:43
 *
 * InstantiationAwareBeanPostProcessor又代表了Spring的另外一段生命周期：实例化。先区别一下Spring Bean的实例化和初始化两个阶段的主要作用：

 1、实例化----实例化的过程是一个创建Bean的过程，即调用Bean的构造函数，单例的Bean放入单例池中

 2、初始化----初始化的过程是一个赋值的过程，即调用Bean的setter，设置Bean的属性

 之前的BeanPostProcessor作用于过程（2）前后，现在的InstantiationAwareBeanPostProcessor则作用于过程（1）前后

 */
public class InstantiationAwareBeanPostProcessorTester implements InstantiationAwareBeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstantiationAwareBeanPostProcessorTester.class);

    @Override
    public Object postProcessBeforeInstantiation(Class<?> aClass, String beanName) throws BeansException {
        LOGGER.debug("spring interface postProcessBeforeInstantiation:{}-{}", aClass.getSimpleName(), beanName);
        return aClass;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        LOGGER.debug("spring interface postProcessAfterInstantiation :{}-{}", bean.getClass().getSimpleName(), beanName);
        return false;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues,
                                                    PropertyDescriptor[] propertyDescriptors, Object bean,
                                                    String beanName) throws BeansException {
        LOGGER.debug("spring interface postProcessPropertyValues");
        return propertyValues;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.debug("spring interface postProcessBeforeInitialization:{}-{}", bean.getClass().getSimpleName(), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.debug("spring interface postProcessAfterInitialization:{}-{}", bean.getClass().getSimpleName(), beanName);
        return bean;
    }
}
