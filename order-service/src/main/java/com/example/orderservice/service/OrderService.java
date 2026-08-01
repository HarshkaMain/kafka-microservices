package com.example.orderservice.service;

import com.example.basedomains.event.OrderCreatedEvent;
import com.example.basedomains.model.Order;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final OrderProducer orderProducer;

    public OrderService(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
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
        log.info("Order created and event published: {}", event);

        return order;
    }
}