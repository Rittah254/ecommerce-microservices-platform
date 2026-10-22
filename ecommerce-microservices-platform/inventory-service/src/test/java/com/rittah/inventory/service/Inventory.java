package com.rittah.inventory.service;

import com.rittah.inventory.entity.Inventory;
import com.rittah.inventory.exception.InsufficientStockException;
import com.rittah.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class InventoryServiceTest {

    @Mock
    private InventoryRepository repository;

    @InjectMocks
    private InventoryService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insufficientStockRejected() {
        Inventory inventory = Inventory.builder()
                .productId(1L)
                .availableQuantity(2)
                .reservedQuantity(0)
                .reorderLevel(1)
                .warehouseCode("WH1")
                .build();

        when(repository.findByProductId(inventory.getProductId()))
                .thenReturn(Optional.of(inventory));

        assertThrows(
                InsufficientStockException.class,
                () -> service.reserve(inventory.getProductId(), 3)
        );
    }
}