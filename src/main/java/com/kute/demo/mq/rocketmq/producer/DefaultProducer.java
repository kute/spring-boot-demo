package com.kute.demo.mq.rocketmq.producer;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.UnsupportedEncodingException;

/**
 * created by kute on 2018-02-11 18:12
 * 非 spring boot 方式
 */
public class DefaultProducer implements InitializingBean, DisposableBean {

    private String group;
    private MessageListener messageListener;
    private String namesrv;
    private String instanceName;

    private volatile static DefaultMQProducer producer = null;

    public DefaultProducer() {
    }

    public DefaultProducer(String group, String namesrv, String instanceName, MessageListener messageListener) {
        this.group = group;
        this.namesrv = namesrv;
        this.instanceName = instanceName;
        this.messageListener = messageListener;
    }

    public void init() {
        if(null == producer) {
            synchronized (DefaultProducer.class) {
                if(null == producer) {
                    producer = new DefaultMQProducer(this.group);
                    producer.setNamesrvAddr(this.namesrv);
                    producer.setInstanceName(this.instanceName);
                }
            }
        }
    }

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new
                DefaultMQProducer("default_producer_group");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest",
                    "TagA" ,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void destroy() throws Exception {
        if(null != producer) {
            producer.shutdown();
        }
    }
}
