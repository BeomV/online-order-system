package com.example.onlineordersystem.controller;

import com.example.onlineordersystem.domain.CreateOrder;
import com.example.onlineordersystem.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/orders")
    public Response<Void> newOrder(
            @RequestBody NewOrderRequest request
    ){
        orderService.newOrder(CreateOrder.builder()
                        .customerId(request.getCustomerId())
                        .storeId(request.getStoreId())
                        .quantityByProduct(request.getProducts())
                .build());

        return Response.success(null);
    }


}
