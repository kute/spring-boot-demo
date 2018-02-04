package com.kute.demo.eventbus;

import com.kute.demo.eventbus.event.MessageEvent;
import com.kute.demo.test.BaseSprintBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by kute on 2018/02/04 11:08
 */
public class EventBusTest extends BaseSprintBootTest {

    @Autowired
    private MessageEventBus messageEventBus;

    @Test
    public void test() {
        messageEventBus.post(new MessageEvent("message event post"));
    }

}
