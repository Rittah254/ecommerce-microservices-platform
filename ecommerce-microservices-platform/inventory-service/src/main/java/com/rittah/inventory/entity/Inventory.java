package com.rittah.inventory.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @Column(nullable = false, unique = true)
    private Long productId;

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private Integer reservedQuantity;

    @Column(nullable = false)
    private Integer reorderLevel;

    @Column(nullable = false)
    private String warehouseCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockStatus stockStatus;

    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

	public int getAvailableQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setAvailableQuantity(int i) {
		// TODO Auto-generated method stub
		
	}

	public int getReservedQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setReservedQuantity(int i) {
		// TODO Auto-generated method stub
		
	}

	public int getReorderLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setStockStatus(StockStatus calculateStatus) {
		// TODO Auto-generated method stub
		
	}

	public void setProductId(Long productId2) {
		// TODO Auto-generated method stub
		
	}

	public void setReorderLevel(Integer reorderLevel2) {
		// TODO Auto-generated method stub
		
	}

	public void setWarehouseCode(String warehouseCode2) {
		// TODO Auto-generated method stub
		
	}
}