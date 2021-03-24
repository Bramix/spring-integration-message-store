package com.example.demospringintegration.configuration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "queue.output")
public interface JmsGateway {

    @Gateway(requestChannel = "inputChannel")
    void sendMessageToQueue(String format);
}
