package com.example.homework.service;

import java.util.Map;

public class ItemService {
    //K-название товара, V-колличество
    private Map<String, Integer> itemMap;

    public ItemService(Map<String, Integer> itemMap) {
        this.itemMap = itemMap;
        itemMap.put("стол", 27);
        itemMap.put("стул", 44);
        itemMap.put("шкаф", 25);
    }

    public void add(String name, Integer amount) {
        itemMap.put(name, amount);
    }

    public boolean isContain(String name) {
        return itemMap.containsKey(name);
    }

    public void remove(String name) {
        itemMap.remove(name);
    }

    public int size() {
        return itemMap.size();
    }
}
