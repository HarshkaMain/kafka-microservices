package com.example.orderservice.service;

import com.example.basedomains.event.OrderCreatedEvent;
import com.example.basedomains.model.Order;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.producer.OrderProducer;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    private final OrderProducer orderProducer;


    public OrderService(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }


    public Order createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .id(1L)
                .customerName("Harsh")
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


        return order;
    }

}
