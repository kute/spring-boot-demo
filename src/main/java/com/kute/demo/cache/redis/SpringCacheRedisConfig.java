package com.kute.demo.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * created by kute on 2018/08/12 10:24
 */
@Configuration
public class SpringCacheRedisConfig extends CachingConfigurerSupport {


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        Map<String, Long> expires = new HashMap<String, Long>();
//        expires.put("user", 6000L);
//        expires.put("city", 600L);
//        cacheManager.setExpires(expires);
//        // Number of seconds before expiration. Defaults to unlimited (0)
//        cacheManager.setDefaultExpiration(600); //设置key-value超时时间
//        return cacheManager;
//    }


}
