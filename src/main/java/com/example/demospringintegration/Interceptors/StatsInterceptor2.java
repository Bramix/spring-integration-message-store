package com.example.demospringintegration.Interceptors;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

public class StatsInterceptor2 implements ChannelInterceptor {
    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        System.out.println("ChannelInterceptor 2 has been finished" + message);
        return message;
    }
}
