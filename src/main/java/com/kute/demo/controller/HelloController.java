package com.kute.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kute on 2017/12/11.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/")
    public String hello() throws Exception{
        throw new Exception("Illegal exception");
    }

    @RequestMapping("/index")
    public String page(ModelMap map) {
        map.put("user", "kute");
        return "index";
    }


}
