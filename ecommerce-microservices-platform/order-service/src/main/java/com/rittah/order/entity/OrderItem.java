package com.rittah.order.entity;
import jakarta.persistence.*;
import com.rittah.order.entity.Order;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name="order_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long orderItemId;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id",nullable=false) private Order order;
    @Column(nullable=false) private Long productId;
    @Column(nullable=false) private String productName;
    @Column(nullable=false) private Integer quantity;
    @Column(nullable=false,precision=15,scale=2) private BigDecimal unitPrice;
    @Column(nullable=false,precision=15,scale=2) private BigDecimal discount;
    @Column(nullable=false,precision=15,scale=2) private BigDecimal tax;
    @Column(nullable=false,precision=15,scale=2) private BigDecimal totalPrice;
	
	
	public void setOrder(Order order2) {
		// TODO Auto-generated method stub
		
	}


	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
