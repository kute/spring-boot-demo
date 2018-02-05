package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-05 20:33
 *
 * 当需要在bean的全部属性设置成功后做些特殊的处理，可以让该bean实现InitializingBean接口。
 效果等同于bean的init-method属性的使用或者@PostContsuct注解的使用。
 三种方式的执行顺序：先注解，然后执行InitializingBean接口中定义的方法，最后执行init-method属性指定的方法

 */
@Component
public class InitializingBeanTester implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializingBeanTester.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("afterPropertiesSet after all bean init over");
    }
}
