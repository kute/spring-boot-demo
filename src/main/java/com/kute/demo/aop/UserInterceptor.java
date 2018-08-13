package com.kute.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by kute on 2017/12/9.
 */
@Order(10)
@Aspect
@Component
public class UserInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(* com.kute.demo.controller..*(..))")
    public Object controllerPointcut(ProceedingJoinPoint pjp){

        // 方法所在类
        String value = pjp.getTarget().getClass().getName();
        logger.debug("Begin go in around pointcut...{}");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        logger.debug("Around get method name [{}] execute .....", methodName);

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            result = "pointcut error return";
        }
        return result;
    }

    @Before("execution(* com.kute.demo.controller..*(..))")
    public void beforePointcut(JoinPoint joinPoint) {
        logger.debug("Before pointcut ....");
    }

}
