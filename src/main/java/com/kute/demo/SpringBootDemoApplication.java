package com.kute.demo;

import com.kute.demo.config.PropertiesBean;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@ImportResource(locations = {"classpath:spring-rest.xml"})
@RestController
@EnableConfigurationProperties({PropertiesBean.class})
@SpringBootApplication
@EnableScheduling
public class SpringBootDemoApplication {

    @Value("${app.name}")
    private String appName;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return appName;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
