package ru.otus.dao.crm.services.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.dao.core.repository.DataTemplate;
import ru.otus.dao.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.dao.crm.model.User;

import java.util.List;
import java.util.Optional;

public class DBServiceUserImpl implements DBServiceUser {
    private final TransactionManagerHibernate transactionManagerHibernate;
    private final DataTemplate<User> dataTemplate;

    private final Logger log = LoggerFactory.getLogger(DBServiceUserImpl.class);

    public DBServiceUserImpl(TransactionManagerHibernate transactionManagerHibernate, DataTemplate dataTemplate) {
        this.transactionManagerHibernate = transactionManagerHibernate;
        this.dataTemplate = dataTemplate;
    }


    @Override
    public void save(User user) {
        transactionManagerHibernate.doInTransaction(
                session -> {
                    var userClone = user.clone();
                    if (user.getId() == null) {
                        dataTemplate.insert(session, userClone);
                        log.info("saved user: {}", user);
                        return userClone;
                    }
                   dataTemplate.update(session, userClone);
                    log.info("update user: {}", userClone);
                    return userClone;
                }
        );
    }

    @Override
    public List<User> findAll() {
        return transactionManagerHibernate.doInTransaction(
                session -> {
                    var userList  = dataTemplate.findAll(session);
                    log.info("client list: {}", userList);
                    return userList;
                }
        );
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return transactionManagerHibernate.doInTransaction(
                session -> {
                    String fieldName = "login";
                    var user = dataTemplate.findByField(session, fieldName,  login);
                    log.info("user: {}", user);
                    return user;
                }
        );
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }
}
