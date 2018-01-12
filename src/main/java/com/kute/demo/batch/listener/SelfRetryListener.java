package com.kute.demo.batch.listener;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.batch.api.chunk.listener.RetryProcessListener;
import javax.batch.api.chunk.listener.RetryReadListener;
import javax.batch.api.chunk.listener.RetryWriteListener;
import java.util.List;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfRetryListener implements RetryReadListener, RetryProcessListener, RetryWriteListener {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void onRetryProcessException(Object o, Exception e) throws Exception {

    }

    @Override
    public void onRetryReadException(Exception e) throws Exception {

    }

    @Override
    public void onRetryWriteException(List<Object> list, Exception e) throws Exception {

    }
}
