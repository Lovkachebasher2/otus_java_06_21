package ru.otus.service;

import ru.otus.entity.Client;

import java.util.List;

public interface ClientService {
    void save(Client client);

    void remove(Long id);

    void removeAll();

    List<Client> findAll();
}
