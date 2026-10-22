package com.rittah.inventory.dto;

import com.rittah.inventory.entity.Inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Available quantity is required")
    @PositiveOrZero(message = "Available quantity cannot be negative")
    private Integer availableQuantity;

    @NotNull(message = "Reserved quantity is required")
    @PositiveOrZero(message = "Reserved quantity cannot be negative")
    private Integer reservedQuantity;

    @NotNull(message = "Reorder level is required")
    @Min(value = 0, message = "Reorder level cannot be negative")
    private Integer reorderLevel;

    @NotBlank(message = "Warehouse code is required")
    private String warehouseCode;

	public Object findByProductId(Long productId2) {
		// TODO Auto-generated method stub
		return null;
	
	}

	public void save(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

	public int getAvailableQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getReservedQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setReservedQuantity(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setAvailableQuantity(int i) {
		// TODO Auto-generated method stub
		
	}

	public int getReorderLevel() {
		// TODO Auto-generated method stub
		return 0;
	
	}

	public Object findByProductIdForUpdate(Long productId2) {
		// TODO Auto-generated method stub
		return null;
	}


}