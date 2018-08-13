package com.kute.demo.shiro.spring.aop;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Booleans;
import com.kute.demo.shiro.spring.annotation.OperationAuthorization;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;

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

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        if (null != method) {
            String methodName = method.getName();
            LOGGER.info("checkRolesOrPermissions methodName[{}] begin", methodName);

            // 工具类
            OperationAuthorization operationAuthorization = AnnotationUtils.getAnnotation(method, OperationAuthorization.class);
//            OperationAuthorization operationAuthorization = method.getAnnotation(OperationAuthorization.class);
            if (null != operationAuthorization && operationAuthorization.enabled()) {

                Subject subject = SecurityUtils.getSubject();
                if (!subject.isAuthenticated()) {
                    LOGGER.warn("Subject[{}] checkRolesOrPermissions for method[{}] is not authenticated", subject, methodName);
                    return;
                }

                String[] rolesAll = operationAuthorization.rolesAll();
                if (rolesAll.length > 0) {
                    Boolean hasAllRoles = subject.hasAllRoles(Sets.newHashSet());
                    if (!hasAllRoles) {
                        throw new UnauthorizedException(Joiner.on("").join(
                                "Subject[", subject, "] does not have all roles [", Arrays.toString(rolesAll), "]"));
                    }
                }

                String[] rolesAny = operationAuthorization.rolesAny();

                if (rolesAny.length > 0) {
                    boolean[] rolesAnyResult = subject.hasRoles(Lists.newArrayList(rolesAny));
                    if (!Booleans.contains(rolesAnyResult, true)) {
                        throw new UnauthorizedException(Joiner.on("").join(
                                "Subject[", subject, "] does not have any role [", Arrays.toString(rolesAny), "]"));
                    }
                }

                String[] permissionAll = operationAuthorization.permissionAll();
                if (permissionAll.length > 0) {
                    Boolean hasAllPermissions = subject.isPermittedAll(permissionAll);
                    if (!hasAllPermissions) {
                        throw new UnauthorizedException(Joiner.on("").join(
                                "Subject[", subject, "] does not have all permissions [", Arrays.toString(permissionAll), "]"));
                    }
                }

                String[] permissionAny = operationAuthorization.permissionAny();
                if (permissionAny.length > 0) {
                    boolean[] permissionAnyResult = subject.isPermitted(permissionAny);
                    if (!Booleans.contains(permissionAnyResult, true)) {
                        throw new UnauthorizedException(Joiner.on("").join(
                                "Subject[", subject, "] does not have any permission [", Arrays.toString(permissionAny), "]"));
                    }
                }

            }
            LOGGER.info("checkRolesOrPermissions methodName[{}] end", methodName);
        }

    }

    private OperationAuthorization getAnnotation(Object obj) {
        if(obj instanceof Method) {
            return AnnotationUtils.findAnnotation((Method) obj, OperationAuthorization.class);
        } else if(obj instanceof AnnotatedElement) {
            return AnnotationUtils.getAnnotation((AnnotatedElement) obj, OperationAuthorization.class);
        }
        return null;
    }

}
