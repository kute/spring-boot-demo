package com.kute.demo.rpc.thrift.simple.test.server;

import com.kute.demo.rpc.thrift.simple.service.HelloWorldService;
import com.kute.demo.rpc.thrift.simple.service.impl.HelloWorldServiceImpl;
import com.kute.demo.rpc.thrift.simple.test.client.HelloWorldClient;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class HelloWorldServer {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServer.class);

    public static void main(String[] args) throws IOException, TTransportException {
        testSimpleServer();
    }

    public static void testSimpleServer() throws IOException, TTransportException {

        HelloWorldService.Processor processor = new HelloWorldService.Processor(new HelloWorldServiceImpl());

        ThriftServerCreator.startSimpleServer(processor);

    }

    public static void test(TServer.Args tArgs) throws IOException, TTransportException {

    }
}
