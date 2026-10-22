package com.rittah.inventory.service;

import com.rittah.inventory.dto.AvailabilityResponse;
import com.rittah.inventory.dto.InventoryResponse;
import com.rittah.inventory.entity.Inventory;
import com.rittah.inventory.entity.StockStatus;
import com.rittah.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository ;


    @Transactional
    public InventoryResponse create(
            Long productId,
            Integer quantity,
            Integer reorderLevel,
            String warehouseCode) {

        Inventory inventory = new Inventory();

        inventory.setProductId(productId);
        inventory.setAvailableQuantity(quantity);
        inventory.setReservedQuantity(0);
        inventory.setReorderLevel(reorderLevel);
        inventory.setWarehouseCode(warehouseCode);

        inventory.setStockStatus(
                calculateStatus(quantity, reorderLevel)
        );

        return toResponse(repository.save(inventory));
    }


    @Transactional(readOnly = true)
    public InventoryResponse get(Long productId) {

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inventory not found for product " + productId));

        return toResponse(inventory);
    }


    @Transactional(readOnly = true)
    public AvailabilityResponse availability(Long productId) {

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inventory not found for product " + productId));

        return new AvailabilityResponse(
                productId,
                inventory.getAvailableQuantity() > 0,
                inventory.getAvailableQuantity()
        );
    }


    @Transactional
    public void reserve(Long productId, int quantity) {

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Inventory not found for product " + productId));

        if (inventory.getAvailableQuantity() < quantity) {

            throw new RuntimeException(
                    "Insufficient stock for product " + productId);
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity);

        inventory.setReservedQuantity(
                inventory.getReservedQuantity() + quantity);

        inventory.setStockStatus(
                calculateStatus(
                        inventory.getAvailableQuantity(),
                        inventory.getReorderLevel()
                )
        );

        repository.save(inventory);
    }


    private StockStatus calculateStatus(
            int quantity,
            int reorderLevel) {

        if (quantity == 0) {
            return StockStatus.OUT_OF_STOCK;
        }

        if (quantity <= reorderLevel) {
            return StockStatus.LOW_STOCK;
        }

        return StockStatus.AVAILABLE;
    }


    private InventoryResponse toResponse(Inventory i) {
		return null;

        // We need to match this to your actual InventoryResponse constructor.

       
    }
}