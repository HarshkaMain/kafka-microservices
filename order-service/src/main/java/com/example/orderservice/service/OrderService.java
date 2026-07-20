package com.example.orderservice.service;

import com.example.orderservice.dto.CreateOrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String createOrder(CreateOrderRequest request) {
        return "Order created for " + request.getProductName();
    }

}
