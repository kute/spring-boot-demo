package com.kute.demo.rpc.thrift.nifty.server;

import com.facebook.nifty.core.*;
import com.facebook.nifty.guice.NiftyModule;
import com.facebook.nifty.processor.NiftyProcessor;
import com.facebook.nifty.processor.NiftyProcessorFactory;
import com.google.inject.Guice;
import com.google.inject.Stage;
import com.kute.demo.rpc.thrift.simple.service.HelloWorldService;
import com.kute.demo.rpc.thrift.simple.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.apache.tomcat.jni.SessionTicketKey;

import javax.inject.Provider;
import java.security.SecureRandom;

public class ThrfitNettyServerCreator {

    public static final int DEFAULT_PORT = 2356;

    private static final NiftyBootstrap bootstrap = Guice.createInjector(
            Stage.PRODUCTION,
            new NiftyModule() {
                @Override
                protected void configureNifty() {
                    SessionTicketKey[] keys = { createFakeSessionTicketKey() };
                    bind().toInstance(
                            ThriftServerDef.newBuilder()
                            .listen(DEFAULT_PORT)
                                    .withProcessor(new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl()))
                            .build()
                    );
                    withNettyServerConfig(NettyConfigProvider.class);
                }
            }
    ).getInstance(NiftyBootstrap.class);

    public static void start() {
        bootstrap.start();

        /**
         *
         * addShutdownHook: 在虚拟机临关闭时被调用
         *
          */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> stop()));
    }

    public static void stop() {
        bootstrap.stop();
    }

    public static class NettyConfigProvider implements Provider<NettyServerConfig>
    {
        @Override
        public NettyServerConfig get()
        {
            NettyServerConfigBuilder nettyConfigBuilder = new NettyServerConfigBuilder();
            nettyConfigBuilder.getSocketChannelConfig().setTcpNoDelay(true);
            nettyConfigBuilder.getSocketChannelConfig().setConnectTimeoutMillis(5000);
            nettyConfigBuilder.getSocketChannelConfig().setTcpNoDelay(true);
            return nettyConfigBuilder.build();
        }
    }

    private static SessionTicketKey createFakeSessionTicketKey() {
        byte[] name = new byte[SessionTicketKey.NAME_SIZE];
        byte[] aesKey = new byte[SessionTicketKey.AES_KEY_SIZE];
        byte[] hmacKey = new byte[SessionTicketKey.HMAC_KEY_SIZE];

        SecureRandom random = new SecureRandom();
        random.nextBytes(name);
        random.nextBytes(aesKey);
        random.nextBytes(hmacKey);
        return new SessionTicketKey(name, aesKey, hmacKey);
    }

}
