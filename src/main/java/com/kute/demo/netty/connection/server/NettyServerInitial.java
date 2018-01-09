package com.kute.demo.netty.connection.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.net.ssl.SSLException;
import javax.servlet.ServletContext;
import java.security.cert.CertificateException;

/**
 * Created by longbai on 2017/12/26.
 */
@Component
public class NettyServerInitial implements InitializingBean, ServletContextAware {

    private static final boolean SSL = System.getProperty("ssl") != null;

    @Autowired
    private NettyServerBootStrap nettyServerBootStrap;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    nettyServerBootStrap.startNettyServer();
//                }catch (Exception e) {
//                }
//            }
//        }).start();
        try {
            nettyServerBootStrap.startNettyServer();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (SSLException e) {
            e.printStackTrace();
        }
    }
}
