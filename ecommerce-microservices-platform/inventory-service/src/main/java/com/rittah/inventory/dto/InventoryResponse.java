package com.rittah.inventory.dto;
import com.rittah.inventory.entity.StockStatus;
import java.time.LocalDateTime;
public record InventoryResponse(Long inventoryId, Long productId, Integer availableQuantity,
    Integer reservedQuantity, Integer reorderLevel, String warehouseCode, StockStatus stockStatus,
    LocalDateTime lastUpdatedAt, LocalDateTime createdAt) {


	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}

	

    }
