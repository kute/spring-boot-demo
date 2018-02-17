package com.kute.demo.rpc.thrift.swift.spring;

import com.kute.demo.rpc.thrift.simple.test.server.ThriftServerCreator;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ThriftApplicationListener implements ApplicationListener<ThriftServerEvent> {

    @Override
    public void onApplicationEvent(ThriftServerEvent thriftServerEvent) {
    }
}
