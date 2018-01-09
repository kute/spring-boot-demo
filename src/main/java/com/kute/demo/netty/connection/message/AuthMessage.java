package com.kute.demo.netty.connection.message;

import com.kute.demo.netty.connection.message.base.BaseMessage;

/**
 * Created by longbai on 2017/12/26.
 */
public class AuthMessage extends BaseMessage {
    private static final long serialVersionUID = 496455511392559925L;

    private String user;
    private String pwd;
}
