package com.rittah.order.controller;
import com.rittah.order.service.OrderService;
import com.rittah.order.dto.OrderRequest;
import com.rittah.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rittah.order.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    // FIX: Declare it as final and remove " = new OrderService()"
    private final OrderService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader(value = "Idempotency-Key", required = false) String key,
                                                @Valid @RequestBody OrderRequest r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r, key));
    }

    @GetMapping
    public Page<OrderResponse> getAll(@PageableDefault(size = 10) Pageable p) {
        return service.getAll(p);
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/customer/{customerId}")
    public Page<OrderResponse> customer(@PathVariable Long customerId, @PageableDefault(size = 10) Pageable p) {
        return service.customer(customerId, p);
    }

    @PutMapping("/{id}/status")
    public OrderResponse status(@PathVariable Long id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
