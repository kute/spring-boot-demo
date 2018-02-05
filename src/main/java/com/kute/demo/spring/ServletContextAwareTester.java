package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * created by kute on 2018/02/05 22:30
 */
@Component
public class ServletContextAwareTester implements ServletContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ServletContextAwareTester.class);

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        logger.debug("spring interface setServletContext:{}", servletContext.getClass().getSimpleName());
        this.servletContext = servletContext;
    }
}
