package com.example.homework.exception;

public class InvokeException extends Exception {

    public InvokeException(String msg, Exception e) {
        super(msg, e);
    }
}
