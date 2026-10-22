package com.rittah.order.dto;
import com.rittah.order.entity.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public record OrderResponse(Long orderId,String orderNumber,Long customerId,LocalDateTime orderDate,
    BigDecimal totalAmount,BigDecimal discountAmount,BigDecimal taxAmount,BigDecimal finalAmount,
    PaymentStatus paymentStatus,OrderStatus orderStatus,String shippingAddress,String city,String state,
    String postalCode,PaymentMethod paymentMethod,LocalDateTime createdAt,LocalDateTime updatedAt,
    List<OrderItemResponse> items){}
