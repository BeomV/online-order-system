package com.example.onlineordersystem.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Table(name = "stores")
public class Store {
    @Id
    private int storeId;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String openAt; // 09:00

    @Column
    private String closeAt; // 18:00
}
