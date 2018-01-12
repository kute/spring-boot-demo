package com.kute.demo.batch.listener;

import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemProcessListener implements ItemProcessListener<Person, Person> {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void beforeProcess(Person person) {
        logger.info("beforeProcess:{}", person);
    }

    @Override
    public void afterProcess(Person from, Person to) {
        logger.info("beforeProcess :from={}, to={}", from, to);
    }

    @Override
    public void onProcessError(Person person, Exception e) {
        logger.info("onProcessError:person={}, err={}", person, e.getMessage());
    }
}
