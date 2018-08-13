package com.kute.demo.annotation;

import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 当 当前 profile 符合条件时创建bean
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({ConditionalOnProfileBean.class})
public @interface ConditionalOnProfile {

    @AliasFor("profile")
    String value() default "";

    @AliasFor("value")
    String profile() default "";
}
