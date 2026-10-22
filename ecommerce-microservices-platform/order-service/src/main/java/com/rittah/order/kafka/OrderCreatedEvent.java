package com.rittah.order.kafka;
import java.util.List;
public record OrderCreatedEvent(String eventId,String eventType,Long orderId,String orderNumber,Long customerId,
    List<OrderItemEvent> items,String createdAt){
    public record OrderItemEvent(Long productId,Integer quantity){}
}
