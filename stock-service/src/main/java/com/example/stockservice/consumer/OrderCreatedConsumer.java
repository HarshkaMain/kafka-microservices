package com.example.stockservice.consumer;


import com.example.basedomains.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderCreatedConsumer {


    @KafkaListener(
            topics = "order-created",
            groupId = "stock-service"
    )
    public void consume(OrderCreatedEvent event) {


        System.out.println(
                "Stock Service received order: "
                + event
        );


    }

}