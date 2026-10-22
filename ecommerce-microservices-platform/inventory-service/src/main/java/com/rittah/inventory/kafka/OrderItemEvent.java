package com.rittah.inventory.kafka;

public record OrderItemEvent(
        Long productId,
        Integer quantity
) {
}

