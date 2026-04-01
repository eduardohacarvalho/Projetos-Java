package com.jpa_spring.jpa_spring.services.exceptions;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String message) {
        super(message);
    }
}
