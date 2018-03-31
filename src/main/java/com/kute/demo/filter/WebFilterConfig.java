package com.kute.demo.filter;

import com.kute.demo.hystrix.filter.HystrixRequestContextServletFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by kute on 2018/03/31 09:15
 */
@Configuration
public class WebFilterConfig extends FilterBeanFactory {

    @Autowired
    private HystrixRequestContextServletFilter hystrixRequestContextServletFilter;

    @Bean
    public FilterRegistrationBean hystrixRequestContextServletFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(hystrixRequestContextServletFilter);
        registration.addUrlPatterns("/*");
        registration.setName("hystrixRequestContextServletFilter");
        registration.setOrder(1);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean shiroFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(delegatingFilterProxy());
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("targetFilterLifecycle", "true");
//        registration.setName("delegatingFilterProxy");
//        registration.setOrder(2);
//        return registration;
//    }

}
