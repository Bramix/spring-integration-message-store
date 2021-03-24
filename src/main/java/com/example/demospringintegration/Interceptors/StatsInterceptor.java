package com.example.demospringintegration.Interceptors;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

public class StatsInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        System.out.println("ChannelInterceptor №1 has been finished");
        return message;
    }
}