package com.kute.demo.config;

import com.kute.demo.autoconfig.AutoConfigBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * created by kute on 2018/08/06 21:18
 */

// 动态创建 bean
@Import({AutoConfigBean.Registrar.class})
@Configuration
public class BootConfiguration {
}
