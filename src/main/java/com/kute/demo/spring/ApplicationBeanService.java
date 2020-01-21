package com.kute.demo.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * created by bailong001 on 2020/01/19 18:21
 */
@Service
@Slf4j
public class ApplicationBeanService implements ApplicationContextAware,
        ApplicationContextInitializer, EnvironmentAware, BeanFactoryAware, BeanFactoryPostProcessor,
        BeanPostProcessor, DisposableBean, InitializingBean, InstantiationAwareBeanPostProcessor,
        ServletContextAware, ServletContainerInitializer, ApplicationListener<ApplicationEvent>,
        ApplicationEventPublisherAware, BeanNameAware, BeanClassLoaderAware, BeanDefinitionCustomizer,
        SmartInstantiationAwareBeanPostProcessor {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("ApplicationContextAware.setApplicationContext");
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("ApplicationContextInitializer.initialize");
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("EnvironmentAware.setEnvironment");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("BeanFactoryAware.setBeanFactory");
    }

    @Override
    public void destroy() throws Exception {
        log.info("DisposableBean.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean.afterPropertiesSet");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("BeanFactoryPostProcessor.postProcessBeanFactory");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        log.info("InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        log.info("InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation");
        return false;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        log.info("InstantiationAwareBeanPostProcessor.postProcessProperties");
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("BeanPostProcessor.postProcessBeforeInitialization");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("BeanPostProcessor.postProcessAfterInitialization");
        return null;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        log.info("ServletContextAware.setServletContext");
    }

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        log.info("ServletContainerInitializer.onStartup");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("ApplicationListener<ApplicationEvent>.onApplicationEvent: " + event.getClass().getSimpleName());
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("BeanClassLoaderAware.setBeanClassLoader");
    }

    @Override
    public void setBeanName(String s) {
        log.info("BeanNameAware.setBeanName");
    }

    @Override
    public void customize(BeanDefinition beanDefinition) {
        log.info("BeanDefinitionCustomizer.customize");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        log.info("ApplicationEventPublisherAware.setApplicationEventPublisher");
    }

//    @Override
//    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
//        log.info("SmartInstantiationAwareBeanPostProcessor.predictBeanType");
//        return beanClass;
//    }

}
