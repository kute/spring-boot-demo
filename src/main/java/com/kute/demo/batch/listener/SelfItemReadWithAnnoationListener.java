package com.kute.demo.batch.listener;

import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 *
 * 注解实现
 */
@Component
public class SelfItemReadWithAnnoationListener{

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @BeforeRead
    public void beforeRead() {

    }

    @AfterRead
    public void afterRead(Person person) {

    }

    @OnReadError
    public void onReadError(Exception e) {

    }
}
