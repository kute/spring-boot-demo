package com.kute.demo.batch.listener;

import org.slf4j.Logger;
import org.springframework.batch.core.annotation.AfterProcess;
import org.springframework.batch.core.annotation.BeforeProcess;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemProcessWithAnnoationListener {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @BeforeProcess
    public void beforeProcess(Throwable throwable) {

    }

    @AfterProcess
    public void afterProcess(Throwable throwable, String s) {

    }

    @OnProcessError
    public void onProcessError(Throwable throwable, Exception e) {

    }
}
