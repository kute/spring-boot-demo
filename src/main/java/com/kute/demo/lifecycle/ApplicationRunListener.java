package com.kute.demo.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * created by kute on 2018/08/02 12:25
 *
 * 监听 spring boot 启动过程中的生命周期事件
 *
 *  META-INF/spring.factories
 */
public class ApplicationRunListener implements SpringApplicationRunListener, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunListener.class);

    private final SpringApplication application;
    private final String[] args;

    public ApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        logger.debug("SpringApplicationRunListener starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
        logger.debug("environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
        logger.debug("contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {
        logger.debug("contextLoaded");
    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {
        logger.debug("finished");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
