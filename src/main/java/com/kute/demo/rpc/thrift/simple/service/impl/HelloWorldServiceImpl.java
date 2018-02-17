package com.kute.demo.rpc.thrift.simple.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kute.demo.rpc.thrift.simple.domain.ResponseCode;
import com.kute.demo.rpc.thrift.simple.domain.UserInfo;
import com.kute.demo.rpc.thrift.simple.exception.InvalidOperation;
import com.kute.demo.rpc.thrift.simple.service.HelloWorldService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class HelloWorldServiceImpl implements HelloWorldService.Iface {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

    @Override
    public ResponseCode sayHello(String username) throws TException {
        return new ResponseCode(200, "hello," + username);
    }

    @Override
    public Map<String, Long> getCounters() throws TException {
        return Maps.newConcurrentMap();
    }

    @Override
    public Set<Integer> getSet() throws TException {
        return Sets.newHashSet(1, 2, 3);
    }

    @Override
    public List<String> getList() throws TException {
        return Lists.newArrayList("a", "b", "c");
    }

    @Override
    public void setOption(String key, String value) throws InvalidOperation, TException {

    }

    @Override
    public UserInfo getUser() throws TException {
        return new UserInfo(1000, "loginKute", "kute", "183", true);
    }

    @Override
    public void release() throws TException {
        logger.info("release method oneway");
    }

    @Override
    public void init() throws TException {
        logger.info("execute super service method: init()");
    }
}
