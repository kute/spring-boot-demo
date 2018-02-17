package com.kute.demo.rpc.thrift.swift.service;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("HelloWorldService")
public interface HelloWorldService extends com.kute.demo.rpc.thrift.swift.service.BaseService, Closeable
{
    @ThriftService("HelloWorldService")
    public interface Async extends com.kute.demo.rpc.thrift.swift.service.BaseService.Async, Closeable
    {
        void close();

        @ThriftMethod(value = "sayHello")
        ListenableFuture<com.kute.demo.rpc.thrift.swift.domain.ResponseCode> sayHello(
            @ThriftField(value=1, name="username", requiredness=Requiredness.NONE) final String username
        );

        @ThriftMethod(value = "getCounters")
        ListenableFuture<Map<String, Long>> getCounters();

        @ThriftMethod(value = "getSet")
        ListenableFuture<Set<Integer>> getSet();

        @ThriftMethod(value = "getList")
        ListenableFuture<List<String>> getList();

        @ThriftMethod(value = "setOption",
                      exception = {
                          @ThriftException(type=com.kute.demo.rpc.thrift.swift.exception.InvalidOperation.class, id=1)
                      })
        ListenableFuture<Void> setOption(
            @ThriftField(value=1, name="key", requiredness=Requiredness.NONE) final String key,
            @ThriftField(value=2, name="value", requiredness=Requiredness.NONE) final String value
        );

        @ThriftMethod(value = "getUser")
        ListenableFuture<com.kute.demo.rpc.thrift.swift.domain.UserInfo> getUser();

        @ThriftMethod(value = "release",
                      oneway = true)
        ListenableFuture<Void> release();
    }
    void close();


    @ThriftMethod(value = "sayHello")
    com.kute.demo.rpc.thrift.swift.domain.ResponseCode sayHello(
        @ThriftField(value=1, name="username", requiredness=Requiredness.NONE) final String username
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "getCounters")
    Map<String, Long> getCounters() throws org.apache.thrift.TException;

    @ThriftMethod(value = "getSet")
    Set<Integer> getSet() throws org.apache.thrift.TException;

    @ThriftMethod(value = "getList")
    List<String> getList() throws org.apache.thrift.TException;

    @ThriftMethod(value = "setOption",
                  exception = {
                      @ThriftException(type=com.kute.demo.rpc.thrift.swift.exception.InvalidOperation.class, id=1)
                  })
    void setOption(
        @ThriftField(value=1, name="key", requiredness=Requiredness.NONE) final String key,
        @ThriftField(value=2, name="value", requiredness=Requiredness.NONE) final String value
    ) throws com.kute.demo.rpc.thrift.swift.exception.InvalidOperation, org.apache.thrift.TException;

    @ThriftMethod(value = "getUser")
    com.kute.demo.rpc.thrift.swift.domain.UserInfo getUser() throws org.apache.thrift.TException;

    @ThriftMethod(value = "release",
                  oneway = true)
    void release() throws org.apache.thrift.TException;
}