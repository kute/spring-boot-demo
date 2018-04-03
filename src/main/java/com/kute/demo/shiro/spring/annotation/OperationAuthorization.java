package com.kute.demo.shiro.spring.annotation;

import java.lang.annotation.*;

/**
 * created on 2018-04-02 16:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperationAuthorization {

    String[] rolesAll() default {};

    String[] rolesAny() default {};

    String[] permissionAll() default {};

    String[] permissionAny() default {};

    boolean enabled() default true;
}
