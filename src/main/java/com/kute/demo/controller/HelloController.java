package com.kute.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kute on 2017/12/11.
 */
@Api(value = "HelloController")
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public String hello() throws Exception{
        throw new Exception("Illegal exception");
    }


}
