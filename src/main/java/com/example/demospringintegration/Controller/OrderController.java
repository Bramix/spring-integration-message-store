package com.example.demospringintegration.Controller;

import com.example.demospringintegration.jpa.pojo.Order;
import com.example.demospringintegration.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order saveOrder(Order order) {
        return orderService.saveOrder(order);
    }
}
