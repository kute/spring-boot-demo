package com.kute.demo.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * Created by kute on 2018/1/10.
 * 定时任务线程池配置
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public ThreadFactory threadFactory() {
        return Executors.defaultThreadFactory();
    }

    /**
     *
     * ThreadPoolExecutor.AbortPolicy: 抛异常
     *
     * ThreadPoolExecutor.CallerRunsPolicy: 在 execute 方法的调用线程中运行被拒绝的任务,只要线程池
     ThreadPoolExecutor.DiscardPolicy; 直接丢弃
     ThreadPoolExecutor.DiscardOldestPolicy; 丢弃队头任务
     * @return
     */
    @Bean
    public RejectedExecutionHandler rejectedExecutionHandler() {
        return new ThreadPoolExecutor.CallerRunsPolicy();
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        ScheduledExecutorService scheduledExecutorService =
                new ScheduledThreadPoolExecutor(10, threadFactory(), rejectedExecutionHandler());
        return scheduledExecutorService;
    }

}
