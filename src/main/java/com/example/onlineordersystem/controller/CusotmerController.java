package com.example.onlineordersystem.controller;

import com.example.onlineordersystem.domain.CreateCustomer;
import com.example.onlineordersystem.domain.Customer;
import com.example.onlineordersystem.domain.CustomerDTO;
import com.example.onlineordersystem.service.CustmoerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CusotmerController {
    private final CustmoerService custmoerService;

    public CusotmerController(CustmoerService custmoerService){
        this.custmoerService = custmoerService;
    }

    @PostMapping("/api/v1/customers")
    public Response<CustomerDTO> createNewCustomer(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phoneNumber
    ) {
        return Response.success(custmoerService.newCustomer(
                CreateCustomer.builder()
                        .address(address)
                        .name(name)
                        .phoneNumber(phoneNumber)
                        .build()
                )
        );
    }


}
