package com.rittah.product.repository;

import com.rittah.product.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductCode(String productCode);
    Optional<Product> findByProductCode(String productCode);
    Page<Product> findByProductNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
    Page<Product> findByBrandContainingIgnoreCase(String brand, Pageable pageable);
    Page<Product> findByProductCodeContainingIgnoreCase(String code, Pageable pageable);
}
