package ru.otus.dao.crm.services.user;


public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceUser userService;

    public UserAuthServiceImpl(DBServiceUser userService) {
        this.userService = userService;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return userService.findByLogin(login)
                .map(user -> user.getPassword().equals(password) && user.getRole().equals("ADMIN"))
                .orElse(false);
    }

}
