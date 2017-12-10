package com.kute.demo.hystrix.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * Hystrix请求上下文
 * Created by kute on 2017/12/10.
 */
public class HystrixRequestContextServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            context.shutdown();
        }
    }

    @Override
    public void destroy() {

    }

}
