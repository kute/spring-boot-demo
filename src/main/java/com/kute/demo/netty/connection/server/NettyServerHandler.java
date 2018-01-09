package com.kute.demo.netty.connection.server;

import com.kute.demo.netty.connection.message.base.BaseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 建立连接时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //建立连接时 存储连接,也可以用户认证成功时存储
        SocketChannel channel = (SocketChannel) ctx.channel();
        if(null != channel) {
//            String clientId = channel.id().toString();
            String clientId = UUID.randomUUID().toString();
            NettyChannelMap.add(clientId, channel);
        }
        super.channelActive(ctx);
    }

    /**
     * 断开连接时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannelMap.remove(((SocketChannel) ctx.channel()));
    }

    /**
     * 异常发生时
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        NettyChannelMap.remove(((SocketChannel) ctx.channel()));
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMessage baseMessage) throws Exception {

    }
}