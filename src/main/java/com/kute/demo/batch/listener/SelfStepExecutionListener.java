package com.kute.demo.batch.listener;

import org.slf4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfStepExecutionListener implements StepExecutionListener {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.debug("beforeStep:{}", stepExecution.getJobExecution().getJobInstance().getJobName());

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("afterStep:{}", stepExecution.getJobExecution().getJobInstance().getJobName());
        return null;
    }
}
