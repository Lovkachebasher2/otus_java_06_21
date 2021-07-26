package com.example.homework.service;

import java.util.Map;

public class UserService {

    private Map<String, Integer> nameAgeMap;

    public UserService(Map<String, Integer> nameAgeMap) {
        this.nameAgeMap = nameAgeMap;
        nameAgeMap.put("Андрей", 27);
        nameAgeMap.put("Артем", 44);
        nameAgeMap.put("Настя", 25);
    }

    public void add(String name, Integer age) {
        nameAgeMap.put(name, age);
    }

    public boolean isContain(String name) {
        return nameAgeMap.containsKey(name);
    }

    public void remove(String name) {
        nameAgeMap.remove(name);
    }

    public int size() {
        return nameAgeMap.size();
    }
}
