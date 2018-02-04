package com.kute.demo.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import com.kute.demo.eventbus.event.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018/02/04 11:09
 */
@Component
public class MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Subscribe
    public void handler(MessageEvent messageEvent) {
        logger.info(messageEvent.toString());
    }

}
