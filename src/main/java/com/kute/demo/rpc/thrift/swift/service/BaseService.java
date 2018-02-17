package com.kute.demo.rpc.thrift.swift.service;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("BaseService")
public interface BaseService extends Closeable
{
    @ThriftService("BaseService")
    public interface Async extends Closeable
    {
        void close();

        @ThriftMethod(value = "init")
        ListenableFuture<Void> init();
    }
    void close();


    @ThriftMethod(value = "init")
    void init() throws org.apache.thrift.TException;
}