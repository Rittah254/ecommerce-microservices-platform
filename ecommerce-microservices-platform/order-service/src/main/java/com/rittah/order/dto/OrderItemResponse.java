package com.rittah.order.dto;
import java.math.BigDecimal;
public record OrderItemResponse(Long orderItemId,Long productId,String productName,Integer quantity,
    BigDecimal unitPrice,BigDecimal discount,BigDecimal tax,BigDecimal totalPrice){}
