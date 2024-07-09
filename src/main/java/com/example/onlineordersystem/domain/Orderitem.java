package com.example.onlineordersystem.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table(name = "order_items")
public class Orderitem {

    @Id
    private int orderItemId;

    @Column
    private int productId;

    @Column
    private int orderId;

    @Column
    private int orderQuantity;

    public Orderitem(int productId, int orderQuantity){
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }

}
