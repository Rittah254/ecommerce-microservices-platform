package com.rittah.product.exception;
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) { super(message); }

    public ProductNotFoundException(Long id) {
        //TODO Auto-generated constructor stub
    }
}
