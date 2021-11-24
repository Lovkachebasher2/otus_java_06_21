package com.example.homework.exception;

public class DbExecException extends RuntimeException{
    public DbExecException(String msg) {
        super(msg);
    }
}
