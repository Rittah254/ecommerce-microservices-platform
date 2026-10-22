package com.rittah.order.client;
import com.rittah.order.dto.AvailabilityResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface InventoryClient {
    @GetExchange("/api/inventory/{productId}/availability")
    AvailabilityResponse check(@PathVariable Long productId);
}
