package com.example.homework;

import java.util.Objects;

public class Customer {

    private final Long id;
    private String name;
    private Long scores;

    //TODO: 1. в этом классе надо исправить ошибки
    public Customer(long id, String name, long scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        new Customer(id, name, scores);
    }

    public Long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        new Customer(id, name, scores);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

