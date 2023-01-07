package com.dh.IntegradorBackend.exections;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }
}
