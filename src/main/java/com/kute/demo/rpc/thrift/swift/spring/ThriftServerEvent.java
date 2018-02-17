package com.kute.demo.rpc.thrift.swift.spring;

import org.springframework.context.ApplicationEvent;

public class ThriftServerEvent extends ApplicationEvent {

    private int port;

    public ThriftServerEvent(Object source) {
        super(source);
    }

    public ThriftServerEvent(Object source, int port) {
        super(source);
        this.port = port;
    }

}
