package com.kute.demo.rpc.thrift.server;

import com.kute.demo.rpc.thrift.service.HelloWorldService;
import com.kute.demo.rpc.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.net.ServerSocket;

public class HelloWorldServer {

    public static void main(String[] args) throws IOException, TTransportException {

        final int SERVER_PORT = 8090;

        ServerSocket socket = new ServerSocket(SERVER_PORT);
        TServerSocket serverTransport = new TServerSocket(socket);

        HelloWorldService.Processor processor = new HelloWorldService.Processor(new HelloWorldServiceImpl());

        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TSimpleServer(tArgs);

        System.out.println("Running server...");
        server.serve();

    }

    public static void testSimpleServer() {

    }
}
