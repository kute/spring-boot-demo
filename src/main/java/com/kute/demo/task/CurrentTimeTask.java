package com.kute.demo.task;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kute on 2018/1/3.
 */
@Component
public class CurrentTimeTask {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        logger.debug("现在时间：{}", dateFormat.format(new Date()));
    }

}
