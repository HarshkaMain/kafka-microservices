package com.example.orderservice.service;

import com.example.basedomains.event.OrderCreatedEvent;
import com.example.basedomains.model.Order;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.producer.OrderProducer;
import com.example.orderservice.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final OrderProducer orderProducer;
private final OrderRepository orderRepository;

    public OrderService(
        OrderProducer orderProducer,
        OrderRepository orderRepository) {

    this.orderProducer = orderProducer;
    this.orderRepository = orderRepository;
}

    public Order createOrder(CreateOrderRequest request) {
        Order order = Order.builder()
        .id(System.currentTimeMillis())
        .customerName(request.getCustomerName())
        .product(request.getProductName())
        .quantity(request.getQuantity())
        .build();

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(order.getId())
                .customerName(order.getCustomerName())
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .build();
        orderProducer.sendMessage(event);
        OrderEntity entity = OrderEntity.builder()
        .id(order.getId())
        .customerName(order.getCustomerName())
        .product(order.getProduct())
        .quantity(order.getQuantity())
        .build();

orderRepository.save(entity);
        log.info("Order created and event published: {}", event);

        return order;
    }
}