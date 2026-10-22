package com.rittah.order.dto;
import java.math.BigDecimal;
public record ProductResponse(Long productId,String productName,String productCode,String description,
    String category,String brand,BigDecimal price,BigDecimal discountPercentage,BigDecimal taxPercentage,String status){}
