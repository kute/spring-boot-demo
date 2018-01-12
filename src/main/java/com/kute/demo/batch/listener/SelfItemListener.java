package com.kute.demo.batch.listener;

import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kute on 2018/1/12.
 *
 * 等价于 三者: SelfItemReadListener  SelfItemProcessListener  SelfItemWritelistener
 *
 */
@Component
public class SelfItemListener extends ItemListenerSupport<String , Person> {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void afterRead(String item) {
        super.afterRead(item);
        logger.debug("afterRead:{}", item);
    }

    @Override
    public void beforeRead() {
        super.beforeRead();
        logger.debug("beforeRead");
    }

    @Override
    public void onReadError(Exception ex) {
        super.onReadError(ex);
        logger.debug("onReadError:{}", ex.getMessage());
    }

    @Override
    public void afterProcess(String item, Person result) {
        super.afterProcess(item, result);
        logger.debug("afterProcess:{},{}", item, result);
    }

    @Override
    public void beforeProcess(String item) {
        super.beforeProcess(item);
        logger.debug("beforeProcess:{}", item);
    }

    @Override
    public void onProcessError(String item, Exception e) {
        super.onProcessError(item, e);
        logger.debug("onProcessError:{},{}", item, e.getMessage());
    }

    @Override
    public void afterWrite(List<? extends Person> item) {
        super.afterWrite(item);
        logger.debug("afterWrite:{}", item);
    }

    @Override
    public void beforeWrite(List<? extends Person> item) {
        super.beforeWrite(item);
        logger.debug("beforeWrite:{}", item);
    }

    @Override
    public void onWriteError(Exception ex, List<? extends Person> item) {
        super.onWriteError(ex, item);
        logger.debug("onWriteError:{},{}", ex.getMessage(), item);
    }
}
