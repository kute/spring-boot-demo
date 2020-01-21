package com.kute.jetcachedemo;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.kute.jetcachedemo.service.UserServiceImpl;
import com.kute.jetcachedemo.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.stream.LongStream;

// 激活 Cached 注解
@EnableMethodCache(basePackages = "com.kute")
// 激活 CreateCache 注解
@EnableCreateCacheAnnotation
@RunWith(SpringRunner.class)
@SpringBootTest
public class JetcacheDemoApplicationTests {

    @Resource
    private UserService userService;

    @Test
    public void contextLoads() {

        LongStream.rangeClosed(1, 10).forEach(i -> {
            System.out.println(userService.getUser(i));
        });

    }

}
