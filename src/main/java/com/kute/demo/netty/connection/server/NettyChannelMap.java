package com.kute.demo.netty.connection.server;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by longbai on 2017/12/26.
 */
public class NettyChannelMap {

    private static Map<String, SocketChannel> socketChannelMap = new ConcurrentHashMap<String, SocketChannel>();

    public static void add(String clientId, SocketChannel socketChannel) {
        socketChannelMap.put(clientId, socketChannel);
    }

    public static Channel get(String clientId) {
        return socketChannelMap.get(clientId);
    }

    public static void remove(String clientId) {
        socketChannelMap.remove(clientId);
    }
    public static void remove(SocketChannel socketChannel) {
        for (Map.Entry entry : socketChannelMap.entrySet()) {
            if (entry.getValue() == socketChannel) {
                socketChannelMap.remove(entry.getKey());
            }
        }
    }

}
