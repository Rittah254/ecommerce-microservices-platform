package com.rittah.product.dto;

import com.rittah.product.entity.ProductStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
    Long productId, String productName, String productCode, String description,
    String category, String brand, BigDecimal price, BigDecimal discountPercentage,
    BigDecimal taxPercentage, ProductStatus status, LocalDateTime createdAt, LocalDateTime updatedAt
) {

    public ProductResponse(Long productId2, String productName2, String productCode2, String description2,
            String category2, String brand2, BigDecimal price2, BigDecimal discountPercentage2,
            BigDecimal taxPercentage2, ProductStatus status2, String imageUrl, String unit) {
        this(productId2, productName2, productCode2, description2, category2, brand2, price2,
            discountPercentage2, taxPercentage2, status2, (LocalDateTime) null, (LocalDateTime) null);
    }}
