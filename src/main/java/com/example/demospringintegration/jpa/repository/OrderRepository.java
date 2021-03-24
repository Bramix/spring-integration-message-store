package com.example.demospringintegration.jpa.repository;

import com.example.demospringintegration.jpa.pojo.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<Order, UUID> {
}
