package com.kute.demo.rpc.thrift.simple.test.client;

import com.kute.demo.rpc.thrift.simple.domain.ResponseCode;
import com.kute.demo.rpc.thrift.simple.service.HelloWorldService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldClient {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldClient.class);

    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;

    private HelloWorldService.Client client;
    private TTransport transport;

    @Test
    public void test() throws TException {

        Assert.assertNotNull(client);
        Assert.assertNotNull(transport);

        ResponseCode result = client.sayHello("kute");
        logger.info("Thrify client call sayHello result = {}", result);

        logger.info("Thrify client call getUser result = {} ", client.getUser());
    }

    @Before
    public void init() throws TTransportException {
        transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
        TProtocol protocol = new TBinaryProtocol(transport);
        client = new HelloWorldService.Client(
                protocol);
        transport.open();
    }

    @After
    public void close() {
        if(null != transport) {
            transport.close();
        }
    }

}
