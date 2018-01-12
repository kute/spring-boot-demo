package com.kute.demo.batch.listener;

import org.slf4j.Logger;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.listener.RepeatListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfRepeatListener extends RepeatListenerSupport {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void before(RepeatContext context) {
        super.before(context);
    }

    @Override
    public void after(RepeatContext context, RepeatStatus result) {
        super.after(context, result);
    }

    @Override
    public void close(RepeatContext context) {
        super.close(context);
    }

    @Override
    public void onError(RepeatContext context, Throwable e) {
        super.onError(context, e);
    }

    @Override
    public void open(RepeatContext context) {
        super.open(context);
    }
}
