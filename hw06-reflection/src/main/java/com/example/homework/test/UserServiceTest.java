package com.example.homework.test;

import com.example.homework.annotation.After;
import com.example.homework.annotation.Before;
import com.example.homework.annotation.Test;
import com.example.homework.exception.NotEqualsException;
import com.example.homework.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceTest {

    public UserServiceTest() {
    }

    private UserService userService;

    @Before
    public void setUp() {
        Map<String, Integer> map = new HashMap<>();
        this.userService = new UserService(map);
    }

    @Test
    public void addTest() throws NotEqualsException {
        int initSize = 3;
        int finalSize = 4;
        if (initSize != userService.size()) {
            throw new NotEqualsException();
        }

        userService.add("Евгений", 21);
        if (finalSize != userService.size()) {
            throw new NotEqualsException();
        }
    }

    @Test
    public void removeTest() throws NotEqualsException {
        int finalSize = 2;
        userService.remove("Настя");
        if (finalSize != userService.size()) {
            throw new NotEqualsException();
        }
    }

    @Test
    public void isContainTest() throws NotEqualsException {
        if (!userService.isContain("Настя")) {
            throw new NotEqualsException();
        }
    }

    @After
    public void close() {
        userService = null;
    }
}
