package com.kute.demo.batch.listener;

import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kute on 2018/1/11.
 */
@Component
public class SelfJobExecutionListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(SelfJobExecutionListener.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        logger.info("After job[{}-{}] execute, status={}", jobExecution.getJobId(), jobExecution.getJobInstance().getJobName(), jobExecution.getStatus().name());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            List<Person> list = jdbcTemplate.query("select first_name, last_name from Person", ((resultSet, i) -> {
                return new Person(resultSet.getString(1), resultSet.getString(2));
            }));
            list.forEach(person -> {
                logger.info("Found person:{}", person);
            });
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("Before job[{}] execute ", jobExecution.getJobId());
        jdbcTemplate.execute("DELETE FROM Person");
    }
}
