
package com.rittah.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderNumber;

    @Column(unique = true)
    private String idempotencyKey;

    private Long customerId;

    private BigDecimal totalAmount;

    private String status;

    private LocalDateTime createdAt;
}
