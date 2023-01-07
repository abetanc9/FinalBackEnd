package com.dh.IntegradorBackend.exections;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
