package com.kute.demo.batch.listener;

import org.slf4j.Logger;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 * <p>
 * SkipListenerSupport
 *
 * 使用注解实现
 */
@Component
public class SelfSkipWithAnnoationListener {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @OnSkipInRead
    public void onSkipInRead(Throwable throwable) {

    }

    @OnSkipInWrite
    public void onSkipInWrite(String s, Throwable throwable) {

    }

    @OnSkipInProcess
    public void onSkipInProcess(Throwable throwable, Throwable throwable2) {

    }
}
