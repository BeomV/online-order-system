package com.example.onlineordersystem.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class CreateOrder {
    private int customerId;
    private Map<Integer, Integer> quantityByProduct;
}
