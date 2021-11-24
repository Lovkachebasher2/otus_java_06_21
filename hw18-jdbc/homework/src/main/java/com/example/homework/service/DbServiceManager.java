package com.example.homework.service;

import com.example.homework.model.Manager;

import java.util.List;
import java.util.Optional;

public interface DbServiceManager {

    Manager saveManager(Manager manager);

    Optional<Manager> getManager(long id);

    List<Manager> findAll();
}
