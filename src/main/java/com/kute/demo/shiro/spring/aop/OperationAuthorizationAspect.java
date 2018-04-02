package com.kute.demo.shiro.spring.aop;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * created on 2018-04-02 16:46
 */
@Order(2)
@Aspect
@Component
public class OperationAuthorizationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationAuthorizationAspect.class);

    @Before("@annotation(com.kute.demo.shiro.spring.annotation.OperationAuthorization)")
    public void checkRolesOrPermissions(JoinPoint joinPoint) throws UnauthorizedException, Throwable {

        LOGGER.info("checkRolesOrPermissions methodName:{}", joinPoint.getSignature().getName());

        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.checkPermission("fruit:apple:eat");
        }
        LOGGER.info("checkRolesOrPermissions check OK:{}", "fruit:apple:eat");

    }

}
