package com.example.homework.exception;

public class TransactionException extends RuntimeException{
    public TransactionException(String msg) {
        super(msg);
    }
}
