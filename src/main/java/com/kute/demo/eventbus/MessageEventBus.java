package com.kute.demo.eventbus;


import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * created by kute on 2018/02/04 11:11
 */
@Configuration
public class MessageEventBus {

    public static final ExecutorService executor =
            new ThreadPoolExecutor(50, 50, 10, TimeUnit.MINUTES,
                    new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    @Bean
    public EventBus eventBus() {
        return new AsyncEventBus(executor);
    }

    public void post(Object event) {
        eventBus().post(event);
    }

    public void post(Object ... events) {
        for (Object event : events) {
            eventBus().post(event);
        }
    }

    public void register(Object listener) {
        eventBus().register(listener);
//        logger.info("register listener:{}", listener);
    }

    public void register(Object ... listeners) {
        for (Object listener : listeners) {
            eventBus().register(listener);
//            logger.info("register listener:{}", listener);
        }
    }

    public void unregister(Object handler) {
        eventBus().unregister(handler);
    }

}
