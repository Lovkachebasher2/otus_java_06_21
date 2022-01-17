package ru.otus.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.entity.Client;
import ru.otus.repository.ClientRepository;
import ru.otus.sessionmanager.TransactionManager;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final TransactionManager transactionManager;

    @Override
    public void save(Client client) {
        transactionManager.doInTransaction(
                () -> {
                    var resultClient = clientRepository.save(client);
                    log.info("saved client: {}", client);
                    return resultClient;
                }
        );
    }

    @Override
    public void remove(Long id) {
        transactionManager.doInTransaction(
                () -> {
                    clientRepository.deleteById(id);
                    log.info("removed client by id: {}", id);
                    return id;
                }
        );
    }

    @Override
    public void removeAll() {
        transactionManager.doInTransaction(
                () -> {
                    clientRepository.deleteAll();
                    log.info("all client have been removed");
                    return null;
                }
        );
    }

    @Override
    public List<Client> findAll() {
        return transactionManager.doInTransaction(
                () -> {
                    List<Client> clientList = clientRepository.findAll();
                    log.info("client list: {}", clientList);
                    return clientList;
                }
        );
    }
}
