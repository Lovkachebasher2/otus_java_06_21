package ru.otus.dao.crm.services.client;

import ru.otus.dao.crm.model.Client;

import java.util.List;
import java.util.Optional;

public interface DBServiceClient {

    void save(Client client);

    List<Client> findAll();

    Optional<Client> findById(Long id);
}
