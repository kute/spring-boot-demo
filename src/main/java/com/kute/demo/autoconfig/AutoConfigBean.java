package com.kute.demo.autoconfig;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * created by kute on 2018/08/06 21:19
 * <p>
 * 使用  BootConfiguration  动态创建bean
 *
 * @see com.kute.demo.config.BootConfiguration
 */
public class AutoConfigBean {

    /**
     * ImportBeanDefinitionRegistrar 接口允许我们 手动向 spring注册bean
     * <p>
     * 动态创建 AutoConfigBean  bean 到 spring
     */
    public static class Registrar implements ImportBeanDefinitionRegistrar {

        private static final String BEAN_NAME = "autoConfigBean";

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

            if (!registry.containsBeanDefinition(BEAN_NAME)) {

                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

                beanDefinition.setBeanClass(AutoConfigBean.class);
                beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
                // 设置 是否 是 手动创建定义的
                beanDefinition.setSynthetic(true);
                registry.registerBeanDefinition(BEAN_NAME, beanDefinition);
            }
        }
    }

}
