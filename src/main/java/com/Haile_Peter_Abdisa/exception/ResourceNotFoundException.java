package com.Haile_Peter_Abdisa.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Product not found: " + id);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
