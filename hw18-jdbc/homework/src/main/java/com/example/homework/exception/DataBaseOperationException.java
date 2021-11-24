package com.example.homework.exception;

public class DataBaseOperationException extends RuntimeException {
    public DataBaseOperationException(String message, Throwable couse) {
        super(message, couse);
    }
}
