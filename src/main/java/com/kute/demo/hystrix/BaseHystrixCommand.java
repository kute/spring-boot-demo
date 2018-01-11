package com.kute.demo.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.kute.demo.po.UserData;
import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kute on 2017/12/9.
 */
public class BaseHystrixCommand extends HystrixCommand<String> {

    private static final Logger logger = LoggerFactory.getLogger(BaseHystrixCommand.class);

    private final String url;
    private final String key;
    private final String method;

    private RestTemplate restTemplate;

    private static final Setter setter = Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix.command.http"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("hystrix.command.http"))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix.command.http"))
            .andCommandPropertiesDefaults(
                    HystrixCommandProperties.Setter()

                            //设置断路器是否打开的错误请求阀值
                            .withCircuitBreakerRequestVolumeThreshold(2)
                                    //设置 在回路被打开，拒绝请求到再次尝试请求并决定回路是否继续打开的时间
                            .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000)
                                    //设置 当错误率%达到多少时断路器打开
                            .withCircuitBreakerErrorThresholdPercentage(20)
                            .withFallbackEnabled(true)
                                    //隔离策略:固定大小线程池
                            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                                    //设置是否在超时时开启中断:默认true
                            .withExecutionIsolationThreadInterruptOnTimeout(true)
                                    //设置启用超时时间设置:默认 true
                            .withExecutionTimeoutEnabled(true)
                                    //执行的超时时间设置
                            .withExecutionTimeoutInMilliseconds(5000)
            );

    public BaseHystrixCommand(RestTemplate restTemplate, String url, String key, String method) {
        super(setter);
        this.restTemplate = restTemplate;
        this.url = url;
        this.key = key;
        this.method = method;
    }

    @Override
    protected String run() throws Exception {
        String result = restTemplate.getForObject(this.url, String.class);
        // 测试 断路器 fallback 调用
//        TimeUnit.MILLISECONDS.sleep(3000);
        return result;
    }

    /**
     * 当熔断机制触发,会调用此方法返回结果
     *
     * @return
     */
    @Override
    protected String getFallback() {
        UserData userData = CacheUtil.getFromLocalCache(this.key);
        if(null != userData) {
            return JSONObject.toJSONString(userData);
        }
        return "fallback get error";
    }
}
