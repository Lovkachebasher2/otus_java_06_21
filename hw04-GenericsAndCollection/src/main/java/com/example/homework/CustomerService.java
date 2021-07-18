package com.example.homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    TreeMap<Customer, String> treeMap = new TreeMap<>(getLongComparator());


    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return treeMap.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return treeMap.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer, data);
    }

    public static Comparator<Customer> getLongComparator() {
        return new LongComparator();
    }

    static class LongComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.getScores().compareTo(o2.getScores());
        }
    }
}
