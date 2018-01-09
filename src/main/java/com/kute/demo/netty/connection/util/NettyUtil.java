package com.kute.demo.netty.connection.util;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * Created by longbai on 2017/12/26.
 */
public class NettyUtil {

    public static EventLoopGroup newEventLoopGroup(int nEventLoops) {
        return new NioEventLoopGroup(nEventLoops);
    }
}
