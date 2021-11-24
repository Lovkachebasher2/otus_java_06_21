package com.example.homework.exception;

public class DataTemplateException extends RuntimeException {
    public DataTemplateException(String message) {
        super(message);
    }

    public DataTemplateException(Throwable cause) {
        super(cause);
    }
}
