package com.kute.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * created by bailong001 on 2020/01/21 10:48
 */
@Service
@RequiredArgsConstructor
public class ApplicationSpringService implements InitializingBean {

    private final ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        ObjectProvider<NormalService> objectProvider = applicationContext.getBeanProvider(NormalService.class);
        objectProvider.ifAvailable(NormalService::done);

        String[] beanNames = applicationContext.getBeanNamesForType(NormalService.class);
    }
}
