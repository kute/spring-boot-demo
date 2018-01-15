package com.kute.demo.batch;

import com.kute.demo.batch.listener.*;
import com.kute.demo.po.Person;
import org.slf4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.ServiceUnavailableException;
import javax.sql.DataSource;

/**
 * Created by kute on 2018/1/10.
 */
//@ComponentScan(basePackageClasses = DefaultBatchConfigurer.class)
@EnableBatchProcessing
@Configuration
public class SelfJobApplication {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource dataSource;

    @Autowired
    private SelfJobExecutionListener selfJobExecutionListener;

    @Autowired
    @Qualifier("flatFileItemReader")
    private FlatFileItemReader flatFileItemReader;

    @Autowired
    @Qualifier("itemProcessor")
    private ItemProcessor itemProcessor;

    @Autowired
    @Qualifier("jdbcBatchItemWriter")
    private JdbcBatchItemWriter jdbcBatchItemWriter;

    @Autowired
    private SelfItemReadListener selfItemReadListener;

    @Autowired
    private SelfItemProcessListener selfItemProcessListener;

    @Autowired
    private SelfItemWritelistener selfItemWritelistener;

    @Autowired
    private SelfChunkListener selfChunkListener;

    @Autowired
    private SelfStepExecutionListener selfStepExecutionListener;

    @Autowired
    private SelfRetryListener selfRetryListener;

    @Autowired
    private SelfSkipListener selfSkipListener;

    @Autowired
    private SelfItemListener selfItemListener;

    /**
     * 使用自定义数据源的 batchconfigurer,否则 无法 配置多个数据源
     *
     * @return
     */
    @Bean
    public BatchConfigurer batchConfigurer() {
        return new DefaultBatchConfigurer(dataSource);
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(flatFileItemReader)
                .processor(itemProcessor)
                .writer(jdbcBatchItemWriter)
                .listener(selfItemReadListener)
                .listener(selfItemProcessListener)
                .listener(selfItemWritelistener)
//                配置容错 retry  skip
                .faultTolerant()
//                对于 failed items ,在此step失败前,设置最大跳过的个数
                .skipLimit(10)
//                申明 如果发生此异常则skip
                .skip(ServiceUnavailableException.class)
//                设置重试上限
                .retryLimit(10)
                .retry(ServiceUnavailableException.class)
                .listener(selfChunkListener)
                .listener(selfRetryListener)
                .listener(selfSkipListener)
                .build();
    }

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(selfJobExecutionListener)
                .start(step1())
                .build();
    }

}
