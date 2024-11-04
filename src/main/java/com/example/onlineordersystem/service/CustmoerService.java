package com.example.onlineordersystem.service;


import com.example.onlineordersystem.domain.CreateCustomer;
import com.example.onlineordersystem.domain.Customer;
import com.example.onlineordersystem.domain.CustomerDTO;
import com.example.onlineordersystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustmoerService {
    private final CustomerRepository customerRepository;

    public CustmoerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDTO newCustomer(CreateCustomer customer){
        Customer entity = Customer.newCustomer(customer);
        Customer saved = customerRepository.save(entity);
        return CustomerDTO.builder()
                .address(saved.getAddress())
                .name(saved.getName())
                .phoneNumber(saved.getPhoneNumber())
                .build();
    }

}
