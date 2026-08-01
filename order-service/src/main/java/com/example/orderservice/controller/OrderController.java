package com.example.orderservice.controller;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.service.OrderService;

import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.basedomains.model.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public Order createOrder(@Valid @RequestBody CreateOrderRequest request) {
    return orderService.createOrder(request);
}



}