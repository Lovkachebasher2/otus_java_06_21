package ru.otus.dao.crm.services.user;

import ru.otus.dao.crm.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

    void save(User user);

    List<User> findAll();

    Optional<User> findByLogin(String login);

    Optional<User> findById(Long id);
}
