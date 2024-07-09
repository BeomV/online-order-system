package com.example.onlineordersystem.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Table(name ="orders")
public class Order {

    @Id
    private int orderId;

    @Column
    private int customerId;

    @Column
    private Timestamp orderAt;

    @MappedCollection(idColumn = "order_item_id", keyColumn = "order_id")
    private List<Orderitem> orderitems = new ArrayList<>();

    public Order(int customerId, List<Orderitem> orderitems){
        this.customerId = customerId;
        this.orderAt = Timestamp.valueOf(LocalDateTime.now());
        this.orderitems = orderitems;
    }

    public static Order newOrder(CreateOrder createOrder){
        List<Orderitem> items = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : createOrder.getQuantityByProduct().entrySet()){
            items.add(new Orderitem(entry.getKey(), entry.getValue()));
        }

        return new Order(createOrder.getCustomerId(), items);
    }

}
