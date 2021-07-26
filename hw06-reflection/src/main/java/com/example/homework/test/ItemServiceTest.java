package com.example.homework.test;

import com.example.homework.annotation.After;
import com.example.homework.annotation.Before;
import com.example.homework.annotation.Test;
import com.example.homework.exception.NotEqualsException;
import com.example.homework.service.ItemService;

import java.util.HashMap;
import java.util.Map;

public class ItemServiceTest {
    public ItemServiceTest() {
    }

    private ItemService itemService;

    @Before
    public void setUp() {
        Map<String, Integer> map = new HashMap<>();
        this.itemService = new ItemService(map);
    }

    @Test(info = "данный тест должен упасть, фреймворк получит ошибку и выведет в консоль, что тест не прошел")
    public void addTest() throws NotEqualsException {
        int finalSize = 3;
        itemService.add("лампа", 21);
        if (finalSize != itemService.size()) {
            throw new NotEqualsException();
        }
    }

    @Test
    public void removeTest() throws NotEqualsException {
        int finalSize = 2;
        itemService.remove("стол");
        if (finalSize == itemService.size()) {
        } else {
            throw new NotEqualsException();
        }
    }

    @After
    public void close() {
        itemService = null;
    }
}
