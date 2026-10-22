package com.rittah.order.kafka;

public record OrderItemEvent(
        Long productId,
        Integer quantity
) {
}
