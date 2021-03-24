package com.example.demospringintegration.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
public class JmsConfiguration {

    @Value("${activemq.broker-url}")
    private String brokerURL;

    @Value("${activemq.user}")
    private String brokerUserName;

    @Value("${activemq.password}")
    private String brokerPassword;

    @Bean
    public ConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerURL);
        activeMQConnectionFactory.setUserName(brokerUserName);
        activeMQConnectionFactory.setPassword(brokerPassword);
        activeMQConnectionFactory.setTrustAllPackages(true);
        return new ActiveMQConnectionFactory();
    }

}
