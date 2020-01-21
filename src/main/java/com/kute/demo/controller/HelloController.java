package com.kute.demo.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kute on 2017/12/11.
 */
@Api(value = "HelloController")
@Controller
@RequestMapping("/hello")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    public String hello() throws Exception {
        throw new Exception("Illegal exception");
    }

    @RequestMapping("/index")
    public String page(ModelMap map) {
        map.put("user", "kute");
        return "index";
    }


}
