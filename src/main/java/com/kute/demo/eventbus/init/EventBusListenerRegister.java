package com.kute.demo.eventbus.init;

import com.google.common.eventbus.Subscribe;
import com.kute.demo.eventbus.MessageEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * created by kute on 2018/02/04 11:12
 */
@Component
public class EventBusListenerRegister implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(EventBusListenerRegister.class);

    @Autowired
    private MessageEventBus messageEventBus;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        for(Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if(annotation.annotationType().equals(Subscribe.class)) {
                    messageEventBus.register(bean);
                    if(logger.isInfoEnabled()) {
                        logger.info("regist bean[{}] to the eventbus", bean.getClass().getName());
                    }
                    return bean;
                }
            }
        }
        return bean;
    }
}
