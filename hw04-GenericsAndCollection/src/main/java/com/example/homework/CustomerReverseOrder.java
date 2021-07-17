package com.example.homework;

import java.util.LinkedList;

public class CustomerReverseOrder {
    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    LinkedList<Customer> customerLinkedList = new LinkedList<>();

    public void add(Customer customer) {
        customerLinkedList.add(customer);
    }

    public Customer take() {
        Customer customer = customerLinkedList.getLast();
        customerLinkedList.removeLast();
        return customer;
    }
}
