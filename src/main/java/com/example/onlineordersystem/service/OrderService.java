package com.example.onlineordersystem.service;

import com.example.onlineordersystem.domain.CreateOrder;
import com.example.onlineordersystem.domain.Order;
import com.example.onlineordersystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void newOrder(CreateOrder createOrder){
        Order entity = Order.newOrder(createOrder);
        orderRepository.save(entity);

    }
}
