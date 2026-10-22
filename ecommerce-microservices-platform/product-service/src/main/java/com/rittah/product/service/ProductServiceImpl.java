package com.rittah.product.service;

import com.rittah.product.dto.ProductRequest;
import com.rittah.product.dto.ProductResponse;
import com.rittah.product.entity.Product;
import com.rittah.product.entity.ProductStatus;
import com.rittah.product.exception.DuplicateProductException;
import com.rittah.product.exception.ProductNotFoundException;
import com.rittah.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl extends ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ProductResponse create(ProductRequest request) {

        if (repository.existsByProductCode(request.productCode())) {
            throw new DuplicateProductException(request.productCode());
        }

        Product product = new Product();
        product.setProductName(request.productName());
        product.setProductCode(request.productCode());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setDiscountPercentage(request.discountPercentage());
        product.setTaxPercentage(request.taxPercentage());
        product.setStatus(request.status() == null
            ? ProductStatus.ACTIVE
            : request.status());
        product.setImageUrl(request.imageUrl() == null ? null : request.imageUrl().toString());
        product.setUnit(request.unit() == null ? null : request.unit().toString());

        return toResponse(repository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return toResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> getAll(Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> search(
            String name,
            String category,
            String brand,
            String productCode,
            Pageable pageable) {

        if (name != null) {
            return repository
                    .findByProductNameContainingIgnoreCase(name, pageable)
                    .map(this::toResponse);
        }

        if (category != null) {
            return repository
                    .findByCategoryContainingIgnoreCase(category, pageable)
                    .map(this::toResponse);
        }

        if (brand != null) {
            return repository
                    .findByBrandContainingIgnoreCase(brand, pageable)
                    .map(this::toResponse);
        }

        if (productCode != null) {
            return repository
                    .findByProductCodeContainingIgnoreCase(productCode, pageable)
                    .map(this::toResponse);
        }

        return repository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setProductName(request.productName());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setDiscountPercentage(request.discountPercentage());
        product.setTaxPercentage(request.taxPercentage());
        product.setStatus(request.status());
        product.setImageUrl(request.imageUrl() == null ? null : request.imageUrl().toString());
        product.setUnit(request.unit() == null ? null : request.unit().toString());

        return toResponse(repository.save(product));
    }

    @Override
    public void delete(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setStatus(ProductStatus.INACTIVE);

        repository.save(product);
    }

    private ProductResponse toResponse(Product p) {

        return new ProductResponse(
                p.getProductId(),
                p.getProductName(),
                p.getProductCode(),
                p.getDescription(),
                p.getCategory(),
                p.getBrand(),
                p.getPrice(),
                p.getDiscountPercentage(),
                p.getTaxPercentage(),
                p.getStatus(),
                p.getImageUrl(),
                p.getUnit()
        );
    }
}
