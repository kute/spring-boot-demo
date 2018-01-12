package com.kute.demo.batch.listener;

import org.slf4j.Logger;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 * <p>
 * SkipListenerSupport
 * 此为实现接口,参考 注解实现: SelfSkipWithAnnoationListener
 */
@Component
public class SelfSkipListener implements SkipListener<Throwable, String> {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void onSkipInRead(Throwable throwable) {


    }

    @Override
    public void onSkipInWrite(String s, Throwable throwable) {

    }

    @Override
    public void onSkipInProcess(Throwable throwable, Throwable throwable2) {

    }
}
