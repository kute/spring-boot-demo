package com.kute.demo.rpc.thrift.simple.test.server;

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

public class ThriftServerCreator {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldServer.class);

    public static final int SERVER_PORT = 8090;

    /**
     * start simple server in 8090
     * @param processor
     * @throws IOException
     * @throws TTransportException
     */
    public static void startSimpleServer(TProcessor processor) throws IOException, TTransportException {
        startSimpleServer(processor, SERVER_PORT);
    }

    public static void startSimpleServer(TProcessor processor, int port) throws IOException, TTransportException {
        logger.info("Thrift server starting ...");

        ServerSocket socket = new ServerSocket(port);

        TServerSocket serverTransport = new TServerSocket(socket);

        TServer.Args tArgs = new TServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TSimpleServer(tArgs);

        logger.info("Thrift server started in localhost:{}", port);
        server.serve();
    }

}
