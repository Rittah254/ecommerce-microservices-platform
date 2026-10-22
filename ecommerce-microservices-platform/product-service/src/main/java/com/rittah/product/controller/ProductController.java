package com.rittah.product.controller;

import com.rittah.product.dto.*;
import com.rittah.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    Page<ProductResponse> all(@PageableDefault(size=10) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    ProductResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping("/search")
    Page<ProductResponse> search(
        @RequestParam(required=false) String name,
        @RequestParam(required=false) String category,
        @RequestParam(required=false) String brand,
        @RequestParam(required=false) String code,
        @PageableDefault(size=10) Pageable pageable) {
        return service.search(name, category, brand, code, pageable);
    }

    @PutMapping("/{id}")
    ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
