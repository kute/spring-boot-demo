package com.kute.demo.netty.connection.client;

import com.kute.demo.netty.connection.util.NettyUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;

/**
 * Created by longbai on 2017/12/26.
 */
@PropertySource("classpath:/netty-config/netty-client.properties")
@Component
public class NettyClientBootstrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final boolean SSL = System.getProperty("ssl") != null;

    @Value("${netty.connect.host}")
    private String host;

    @Value("${netty.connect.port}")
    private int port;

    @Value("${netty.group.loops}")
    private int groupLoops;

    @Value("${netty.connect.timeout}")
    private int connectTimeOut;

    private SslContext sslCtx;

    public Boolean connect() throws SSLException {
        if (SSL) {
            sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
        } else {
            sslCtx = null;
        }
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = NettyUtil.newEventLoopGroup(groupLoops);
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc()));
                            }
                            p.addLast("idle", new IdleStateHandler(300, 300, 300));
                            p.addLast(new ObjectEncoder());
                            p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                            p.addLast(new NettyClientHandler());
                        }
                    });

            //设置TCP协议的属性
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.SO_TIMEOUT, connectTimeOut);
            ChannelFuture f = bootstrap.connect(host, port).sync();
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {

                    } else {

                    }
                }
            });
            f.channel().closeFuture().sync();
        } catch (Exception e) {
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
        return Boolean.FALSE;
    }
}
