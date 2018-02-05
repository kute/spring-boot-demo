package com.kute.demo.spring;

import org.springframework.context.ApplicationEvent;

/**
 * created by kute on 2018-02-05 20:25
 * 创建自定义事件
 */
public class ApplicationEventTester extends ApplicationEvent {

    private String name;

    public ApplicationEventTester(Object source) {
        super(source);
    }

    public ApplicationEventTester(Object source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public String toString() {
        return "ApplicationEventTester{" +
                "name='" + name + '\'' +
                "source='" + this.getSource() + '\'' +
                '}';
    }
}
