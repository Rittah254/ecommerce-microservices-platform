package com.rittah.order.dto;
import com.rittah.order.entity.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
public record OrderRequest(
    @NotNull Long customerId,
    @NotEmpty List<@Valid OrderItemRequest> items,
    @NotBlank String shippingAddress,
    @NotBlank String city,
    @NotBlank String state,
    @NotBlank String postalCode,
    @NotNull PaymentMethod paymentMethod
){}
