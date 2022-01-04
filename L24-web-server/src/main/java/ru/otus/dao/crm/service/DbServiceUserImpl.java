package ru.otus.dao.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.dao.core.repository.DataTemplate;
import ru.otus.dao.core.sessionmanager.TransactionManager;
import ru.otus.model.User;

import java.util.List;
import java.util.Optional;

public class DbServiceUserImpl implements DBServiceUser {
    private static final Logger log = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final DataTemplate<User> userDataTemplate;
    private final TransactionManager transactionManager;

    public DbServiceUserImpl(TransactionManager transactionManager, DataTemplate<User> userDataTemplate) {
        this.transactionManager = transactionManager;
        this.userDataTemplate = userDataTemplate;
    }

    @Override
    public void save(User user) {
        transactionManager.doInTransaction(session -> {
            var userClone = user.clone();
            if (user.getId() == null) {
                userDataTemplate.insert(session, userClone);
                log.info("created client: {}", userClone);
                return userClone;
            }
            userDataTemplate.update(session, userClone);
            log.info("updated client: {}", userClone);
            return userClone;
        });
    }

    @Override
    public Optional<User> findById(long id) {
        return transactionManager.doInTransaction(
                session -> {
                    Optional<User> userOptional = userDataTemplate.findById(session, id);
                    log.info("user: {}", userOptional);
                    return userOptional;
                }
        );
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return transactionManager.doInTransaction(
                session -> {
                    String fieldName = "login";
                    Optional<User> user = userDataTemplate.findByField(session, fieldName, login);
                    log.info("user: {}", user.get());
                    return user;
                }
        );
    }

    @Override
    public List<User> findAll() {
        return transactionManager.doInTransaction(
                session -> {
                    List<User> userList = userDataTemplate.findAll(session);
                    log.info("users: {}", userList);
                    return userList;
                }
        );
    }
}
