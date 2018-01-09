package com.kute.demo.netty.connection.client;

import com.kute.demo.netty.connection.message.base.BaseMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by longbai on 2017/12/26.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<BaseMessage> {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMessage baseMessage) throws Exception {

    }
}
