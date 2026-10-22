package com.rittah.order.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate = null;

    public void publish(OrderCreatedEvent event) {

        log.info(
                "Publishing ORDER_CREATED event for order {}",
                event.orderId()
        );

        kafkaTemplate.send(
                "order-events",
                event.orderId().toString(),
                event
        );
    }
}
