package com.kute.jetcachedemo;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 激活 Cached 注解
@EnableMethodCache(basePackages = "com.kute")
// 激活 CreateCache 注解
@EnableCreateCacheAnnotation
@SpringBootApplication
public class JetcacheDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JetcacheDemoApplication.class, args);
    }

}
