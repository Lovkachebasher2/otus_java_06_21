package com.homework.service;

import com.homework.entity.User;

import java.util.List;

public interface UserService {


    User getById(Long id);

    List<User> getAll();

    void deleteById(Long id);

    void deleteAll();

    void createUser(User user);
}
