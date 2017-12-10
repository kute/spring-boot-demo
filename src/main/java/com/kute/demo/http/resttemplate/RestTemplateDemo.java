package com.kute.demo.http.resttemplate;

import com.alibaba.fastjson.JSONObject;
import com.kute.demo.po.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by kute on 2017/12/9.
 */

@RestController
public class RestTemplateDemo {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

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

    @GetMapping("/rest/api")
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
}
