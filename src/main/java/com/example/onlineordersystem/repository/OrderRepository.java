package com.example.onlineordersystem.repository;

import com.example.onlineordersystem.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
