package com.kute.demo.mq.rocketmq.filter;

import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * created by kute on 2018-02-11 19:04
 */
public class DefaultFilter implements MessageFilter {

    @Override
    public boolean match(MessageExt msg, FilterContext context) {
        return false;
    }
}
