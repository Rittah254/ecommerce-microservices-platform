package com.rittah.order.service;

import com.rittah.order.entity.OrderStatus;
import com.rittah.order.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long orderId,
        String orderNumber,
        Long customerId,
        LocalDateTime orderDate,
        BigDecimal finalAmount,
        PaymentStatus paymentStatus,
        OrderStatus orderStatus
) {
}
