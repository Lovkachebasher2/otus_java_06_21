package ru.otus.dao.crm.service;

import ru.otus.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    void save(User user);
    Optional<User> findById(long id);
    Optional<User> findByLogin(String login);
    List<User> findAll();
}
