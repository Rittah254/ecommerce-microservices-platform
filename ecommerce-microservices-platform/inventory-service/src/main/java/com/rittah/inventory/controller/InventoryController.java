package com.rittah.inventory.controller;

import com.rittah.inventory.dto.AvailabilityResponse;
import com.rittah.inventory.dto.InventoryResponse;
import com.rittah.inventory.service.InventoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@Validated
public class InventoryController {

    private final InventoryService inventoryService;

    // Constructor
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse create(
            @Valid @RequestBody CreateInventoryRequest req) {

        return inventoryService.create(
                req.productId(),
                req.quantity(),
                req.reorderLevel(),
                req.warehouseCode());
    }

    @GetMapping("/{productId}")
    public InventoryResponse get(@PathVariable Long productId) {
        return inventoryService.get(productId);
    }

    @GetMapping("/{productId}/availability")
    public AvailabilityResponse availability(
            @PathVariable Long productId) {

        return inventoryService.availability(productId);
    }

    @PostMapping("/{productId}/reserve")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reserve(
            @PathVariable Long productId,
            @RequestParam @Min(1) int quantity) {

        inventoryService.reserve(productId, quantity);
    }
}