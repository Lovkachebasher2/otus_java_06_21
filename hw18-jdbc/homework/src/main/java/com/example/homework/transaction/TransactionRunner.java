package com.example.homework.transaction;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);
}
