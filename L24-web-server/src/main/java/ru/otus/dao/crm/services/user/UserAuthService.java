package ru.otus.dao.crm.services.user;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
