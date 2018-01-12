package com.kute.demo.batch.listener;

import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfItemWriteWithAnnoationlistener {

    private static final Logger logger = LoggerFactory.getLogger(SelfItemWriteWithAnnoationlistener.class);

    @BeforeWrite
    public void beforeWrite(List<? extends Person> list) {
        logger.info("Item write listener beforeWrite:{}", list.size());
    }

    @AfterWrite
    public void afterWrite(List<? extends Person> list) {
        logger.info("Item write listener afterWrite:{}", list.size());
    }

    @OnWriteError
    public void onWriteError(Exception e, List<? extends Person> list) {
        logger.info("Item write listener onWriteError:error={}, size={}", e.getMessage(), list.size());
    }
}
