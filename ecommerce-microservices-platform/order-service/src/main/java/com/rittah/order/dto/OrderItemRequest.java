package com.rittah.order.dto;
import jakarta.validation.constraints.*;
public record OrderItemRequest(@NotNull Long productId,@NotNull @Positive Integer quantity){}
