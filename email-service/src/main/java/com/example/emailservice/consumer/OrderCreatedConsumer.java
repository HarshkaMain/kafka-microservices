package com.example.emailservice.consumer;

import com.example.basedomains.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "email-service"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println(
                "Sending email to "
                        + event.getCustomerName()
                        + " for product "
                        + event.getProduct()
        );

    }

}