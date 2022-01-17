package ru.otus.dao.crm.services.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.dao.core.repository.DataTemplate;
import ru.otus.dao.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.dao.crm.model.Client;

import java.util.List;
import java.util.Optional;

public class DBServiceClientImpl implements DBServiceClient {
    private final TransactionManagerHibernate transactionManagerHibernate;
    private final DataTemplate<Client> dataTemplate;

    private final Logger log = LoggerFactory.getLogger(DBServiceClientImpl.class);

    public DBServiceClientImpl(TransactionManagerHibernate transactionManagerHibernate, DataTemplate<Client> dataTemplate) {
        this.transactionManagerHibernate = transactionManagerHibernate;
        this.dataTemplate = dataTemplate;
    }


    @Override
    public void save(Client client) {
        transactionManagerHibernate.doInTransaction(
                session -> {
                    var clientClone = client.clone();
                    if (client.getId() == null) {
                        dataTemplate.insert(session, clientClone);
                        log.info("client saved: {}", clientClone);
                        return clientClone;
                    }
                    dataTemplate.update(session, clientClone);
                    log.info("client updated: {}", clientClone);
                    return clientClone;
                }
        );
    }

    @Override
    public List<Client> findAll() {
        return transactionManagerHibernate.doInTransaction(
                session -> {
                    List<Client> clientList = dataTemplate.findAll(session);
                    log.info("client list: {}", clientList) ;
                    return clientList;
                }
        );
    }

    @Override
    public Optional<Client> findById(Long id) {
        return transactionManagerHibernate.doInTransaction(
                session -> {
                    Optional<Client> client = dataTemplate.findById(session, id);
                    log.info("client: {}", client);
                    return client;
                }
        );
    }
}
