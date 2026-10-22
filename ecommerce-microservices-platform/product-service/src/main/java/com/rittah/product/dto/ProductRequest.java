package com.rittah.product.dto;

import com.rittah.product.entity.ProductStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank @Size(max=150) String productName,
    @NotBlank @Size(max=80) String productCode,
    @Size(max=1000) String description,
    @NotBlank @Size(max=80) String category,
    @NotBlank @Size(max=80) String brand,
    @NotNull @Positive BigDecimal price,
    @NotNull @DecimalMin("0.0") @DecimalMax("100.0") BigDecimal discountPercentage,
    @NotNull @DecimalMin("0.0") @DecimalMax("100.0") BigDecimal taxPercentage,
    @NotNull ProductStatus status
) {

    public Object imageUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'imageUrl'");
    }

    public Object unit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unit'");
    }}
