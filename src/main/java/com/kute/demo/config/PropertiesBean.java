package com.kute.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by kute on 2017/12/7.
 */
@ConfigurationProperties(prefix = "com.kute")
public class PropertiesBean {

    private String name = "kute";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
