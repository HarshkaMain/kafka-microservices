package com.example.orderservice.producer;

import com.example.basedomains.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {


    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;


    public OrderProducer(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(OrderCreatedEvent event) {

        kafkaTemplate.send(
                "order-created",
                event
        );

    }

}
