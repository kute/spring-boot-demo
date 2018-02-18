package com.kute.demo.rpc.thrift.swift.service.impl;

import com.kute.demo.rpc.thrift.swift.domain.ResponseCode;
import com.kute.demo.rpc.thrift.swift.domain.UserInfo;
import com.kute.demo.rpc.thrift.swift.exception.InvalidOperation;
import com.kute.demo.rpc.thrift.swift.service.HelloWorldService;
import org.apache.thrift.TException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public void close() {

    }

    @Override
    public void init() throws TException {

    }

    @Override
    public ResponseCode sayHello(String username) throws TException {
        return null;
    }

    @Override
    public Map<String, Long> getCounters() throws TException {
        return null;
    }

    @Override
    public Set<Integer> getSet() throws TException {
        return null;
    }

    @Override
    public List<String> getList() throws TException {
        return null;
    }

    @Override
    public void setOption(String key, String value) throws InvalidOperation, TException {

    }

    @Override
    public UserInfo getUser() throws TException {
        return null;
    }

    @Override
    public void release() throws TException {

    }
}
