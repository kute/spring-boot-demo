package com.kute.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * created by bailong001 on 2020/01/21 10:50
 */
@Service
@Slf4j
public class NormalService {

    public String done() {
        if (log.isInfoEnabled()) {
            log.info("done");
        }
        return "done";
    }

}
