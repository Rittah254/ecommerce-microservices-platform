package com.rittah.inventory.kafka;
import java.util.List;
public record OrderCreatedEvent(String eventId,String eventType,Long orderId,String orderNumber,Long customerId,
    List<OrderCreatedItem> items,String createdAt) {
    public record OrderCreatedItem(Long productId,Integer quantity){}
}
