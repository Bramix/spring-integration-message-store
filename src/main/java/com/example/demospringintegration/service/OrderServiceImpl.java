package com.example.demospringintegration.service;

import com.example.demospringintegration.configuration.JmsGateway;
import com.example.demospringintegration.jpa.pojo.Order;
import com.example.demospringintegration.jpa.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final JmsGateway jmsGateway;

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        var savedOrder = orderRepository.save(order);
        jmsGateway.sendMessageToQueue(String.format("Order with id = %s has been successfully created", savedOrder.getId()));
        return savedOrder;
    }

}
