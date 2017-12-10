package com.kute.demo.controller;

import com.kute.demo.config.PropertiesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kute on 2017/12/7.
 */
@RestController
public class UserController {

    @Autowired
    private PropertiesBean propertiesBean;

    @GetMapping("/name")
    public String getName() {
        return propertiesBean.getName();
    }

}
