package com.rittah.inventory.dto;
public record AvailabilityResponse(Long productId, boolean available, Integer availableQuantity) {}
