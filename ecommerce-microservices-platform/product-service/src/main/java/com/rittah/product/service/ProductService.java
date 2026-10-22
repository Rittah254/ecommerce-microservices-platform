package com.rittah.product.service;

import com.rittah.product.dto.*;
import com.rittah.product.entity.Product;
import com.rittah.product.exception.*;
import com.rittah.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository repository;

    @Transactional
    public ProductResponse create(ProductRequest r) {
        if (repository.existsByProductCode(r.productCode()))
            throw new DuplicateProductException("Product code already exists: " + r.productCode());
        Product p = new Product();
        p.setProductName(r.productName());
        p.setProductCode(r.productCode());
        p.setDescription(r.description());
        p.setCategory(r.category());
        p.setBrand(r.brand());
        p.setPrice(r.price());
        p.setDiscountPercentage(r.discountPercentage());
        p.setTaxPercentage(r.taxPercentage());
        p.setStatus(r.status());
        log.info("Creating product {}", r.productCode());
        return toResponse(repository.save(p));
    }

    @Transactional(readOnly = true)
    public ProductResponse get(Long id) {
        return toResponse(repository.findById(id).orElseThrow(() ->
            new ProductNotFoundException("Product with ID " + id + " was not found")));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> search(String name, String category, String brand, String code, Pageable pageable) {
        if (name != null && !name.isBlank()) return repository.findByProductNameContainingIgnoreCase(name, pageable).map(this::toResponse);
        if (category != null && !category.isBlank()) return repository.findByCategoryContainingIgnoreCase(category, pageable).map(this::toResponse);
        if (brand != null && !brand.isBlank()) return repository.findByBrandContainingIgnoreCase(brand, pageable).map(this::toResponse);
        if (code != null && !code.isBlank()) return repository.findByProductCodeContainingIgnoreCase(code, pageable).map(this::toResponse);
        return getAll(pageable);
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest r) {
        Product p = repository.findById(id).orElseThrow(() ->
            new ProductNotFoundException("Product with ID " + id + " was not found"));
        repository.findByProductCode(r.productCode()).ifPresent(existing -> {
            if (!existing.getProductId().equals(id))
                throw new DuplicateProductException("Product code already exists: " + r.productCode());
        });
        p.setProductName(r.productName()); p.setProductCode(r.productCode()); p.setDescription(r.description());
        p.setCategory(r.category()); p.setBrand(r.brand()); p.setPrice(r.price());
        p.setDiscountPercentage(r.discountPercentage()); p.setTaxPercentage(r.taxPercentage()); p.setStatus(r.status());
        return toResponse(repository.save(p));
    }

    @Transactional
    public void delete(Long id) {
        Product p = repository.findById(id).orElseThrow(() ->
            new ProductNotFoundException("Product with ID " + id + " was not found"));
        p.setStatus(com.rittah.product.entity.ProductStatus.INACTIVE);
        repository.save(p);
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(p.getProductId(), p.getProductName(), p.getProductCode(), p.getDescription(),
            p.getCategory(), p.getBrand(), p.getPrice(), p.getDiscountPercentage(), p.getTaxPercentage(),
            p.getStatus(), p.getCreatedAt(), p.getUpdatedAt());
    }

    public ProductResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
}
