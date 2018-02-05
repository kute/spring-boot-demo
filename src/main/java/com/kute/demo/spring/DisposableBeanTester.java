package com.kute.demo.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * created by kute on 2018-02-05 20:35
 *
 * 当需要在bean销毁之前做些特殊的处理，可以让该bean实现DisposableBean接口。
 效果等同于bean的destroy-method属性的使用或者@PreDestory注解的使用。
 三种方式的执行顺序：先注解，然后执行DisposableBean接口中定义的方法，最后执行destroy-method属性指定的方法

 */
@Component
public class DisposableBeanTester implements DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisposableBeanTester.class);

    @Override
    public void destroy() throws Exception {
        LOGGER.debug("spring interface destroy before all bean destroy");
    }
}
