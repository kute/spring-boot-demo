package com.kute.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.kute.demo.po.User;
import com.kute.demo.po.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by kute on 2017/12/9.
 */

@RestController
@RequestMapping("/rest")
public class RestTemplateDemo {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/getuser")
    public String get() {
        UserData userData = new UserData("1001", "kute");
        return JSONObject.toJSONString(userData);
    }

    @GetMapping("/rest/getuser")
    public String get1() {
        String url = "http://localhost:8080/getuser";
        try {
            ResponseEntity<UserData> responseEntity = restTemplate.getForEntity(url, UserData.class);
            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                UserData userData = responseEntity.getBody();
                userData.setUserName("rest template name");
                return JSONObject.toJSONString(userData);
            }
        } catch (HttpClientErrorException hce) {
            logger.error("request httpclient error:", hce);
        } catch (Exception e) {
            logger.error("request error", e);
        }
        return null;
    }

    @GetMapping("/api")
    public String get2() {
        String url = "https://api.cdnjs.com/libraries/1140";
        try {
            JSONObject response = restTemplate.getForObject(url, JSONObject.class);
            response.put("name", response.getString("name") + "-new-name");
            return response.toJSONString();
        } catch (HttpClientErrorException hce) {
            logger.error("request httpclient error:", hce);
        } catch (Exception e) {
            logger.error("request error", e);
        }
        return null;
    }

    @GetMapping(value = "/serialTest", produces = "application/json")
    public Object serialTest() {

        List<String> list = Lists.newArrayList();

        IntStream.range(1, 10).forEach(i -> {
            list.add("kute" + i);
            redisTemplate.opsForValue().set("kute" + i, JSONObject.toJSONString(new User(i)));
        });

        List<String> result = redisTemplate.opsForValue().multiGet(list).stream().filter(Predicates.notNull()).collect(Collectors.toList());
        return result;
    }

}
