package com.gruzdov.test_project.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id) {
        super("Resource with ID :" + id + " not found");
    }
}
