package com.homework.service;

import com.homework.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, User> userMap = new HashMap<>();

    public UserServiceImpl() {
        userMap.put(1L, new User(1L, "alfred", 22));
        userMap.put(2L, new User(2L, "Dwayne", 22));
        userMap.put(3L, new User(3L, "Mile", 22));
    }

    @Override
    public User getById(Long id) {
        checkContainUserById(id);
        return userMap.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void deleteById(Long id) {
        checkContainUserById(id);
        userMap.remove(id);
    }

    @Override
    public void deleteAll() {
        userMap.clear();
    }

    @Override
    public void createUser(User user) {
        userMap.put(user.getId(), user);
    }

    private void checkContainUserById(Long id) {
        if (!userMap.containsKey(id)) {
            throw new RuntimeException("user with id: " + id + " is not contain!");
        }
    }
}
