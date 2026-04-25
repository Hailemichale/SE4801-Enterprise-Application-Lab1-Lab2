package com.Haile_Peter_Abdisa.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final Long id;

    public ResourceNotFoundException(Long id) {
        super("Product " + id + " not found");
        this.id = id;
    }

    public Long getId() { return id; }
}
