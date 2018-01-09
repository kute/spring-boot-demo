package com.kute.demo.netty.connection.server;

import com.google.common.base.Joiner;
import com.kute.demo.netty.connection.util.NettyUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

/**
 * Created by longbai on 2017/12/26.
 */
@PropertySource("classpath:/netty-config/netty-server.properties")
@Component
public class NettyServerBootStrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final boolean SSL = System.getProperty("ssl") != null;

    @Value("${netty.connect.host}")
    private String host;

    @Value("${netty.connect.port}")
    private int port;

    @Value("${netty.group.parent.size}")
    private int parentLoops;

    @Value("${netty.group.child.size}")
    private int childLoops;

    @Value("${netty.connect.timeout}")
    private long connectTimeOut;

    public Boolean startNettyServer() throws CertificateException, SSLException {
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContext.newServerContext(ssc.certificate(),
                    ssc.privateKey());
        } else {
            sslCtx = null;
        }

        ServerBootstrap server = new ServerBootstrap();
        EventLoopGroup parentGroup = NettyUtil.newEventLoopGroup(parentLoops);
        EventLoopGroup chilldGroup = NettyUtil.newEventLoopGroup(childLoops);
        try {
            server.group(parentGroup, chilldGroup)
                    .channel(NioServerSocketChannel.class)
                    // 初始服务端可接收连接的个数
                    .option(ChannelOption.SO_BACKLOG, 2048)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(socketChannel.alloc()));
                            }
                            p.addLast("idle", new IdleStateHandler(300, 300, 300));
                            p.addLast(new ObjectEncoder());
                            p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                            p.addLast(new NettyServerHandler());
                        }
                    });
            // Start the server.
            ChannelFuture f = server.bind(host, port).sync();
            // Wait until the server socket is closed.
            if(f.await(connectTimeOut, TimeUnit.MILLISECONDS)) {
                logger.info("Netty server[{}:{}] started", host, port);
                return Boolean.TRUE;
            } else {
                logger.error("Netty server[{}:{}] start timeout", host, port);
            }
            f.channel().closeFuture().sync();
        }catch (Exception e) {
            logger.error(Joiner.on(" ").join("Start netty server[", host, ":", port, "] error"), e);
        } finally {
            // Shut down all event loops to terminate all threads.
            parentGroup.shutdownGracefully();
            chilldGroup.shutdownGracefully();
        }
        return Boolean.FALSE;
    }

}
