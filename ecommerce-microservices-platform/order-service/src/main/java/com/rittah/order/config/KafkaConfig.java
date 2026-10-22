package com.rittah.order.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaConfig {
    @Bean NewTopic orderEventsTopic(){return TopicBuilder.name("order-events").partitions(3).replicas(1).build();}
}
