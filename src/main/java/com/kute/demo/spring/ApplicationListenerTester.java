package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-05 20:29
 * 监听自定义事件
 */
@Component
public class ApplicationListenerTester implements ApplicationListener<ApplicationEventTester> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListenerTester.class);

    @Override
    public void onApplicationEvent(ApplicationEventTester applicationEventTester) {
        LOGGER.debug("spring interface onApplicationEvent event:{}", applicationEventTester);
    }
}
