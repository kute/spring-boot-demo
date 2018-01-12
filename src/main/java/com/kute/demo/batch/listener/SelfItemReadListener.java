package com.kute.demo.batch.listener;

import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemReadListener implements ItemReadListener<Person> {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void beforeRead() {
        logger.info("beforeRead");
    }

    @Override
    public void afterRead(Person person) {
        logger.info("afterRead");
    }

    @Override
    public void onReadError(Exception e) {
        logger.info("onReadError:{}", e.getMessage());
    }
}
