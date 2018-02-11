package com.kute.demo.mq.rocketmq.callback;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-11 18:53
 */
@Component
public class DefaultCallBack implements SendCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCallBack.class);

    @Override
    public void onSuccess(SendResult sendResult) {
        LOGGER.info(sendResult.toString());
    }

    @Override
    public void onException(Throwable e) {
        LOGGER.error("", e);
    }
}
