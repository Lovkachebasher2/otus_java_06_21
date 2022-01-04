package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.cfg.Configuration;
import ru.otus.dao.core.repository.DataTemplateHibernate;
import ru.otus.dao.core.repository.HibernateUtils;
import ru.otus.dao.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.dao.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.dao.crm.service.DBServiceUser;
import ru.otus.dao.crm.service.DbServiceUserImpl;
import ru.otus.model.User;
import ru.otus.server.UsersWebServer;
import ru.otus.server.UsersWebServerWithFilterBasedSecurity;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;
import ru.otus.services.UserAuthService;
import ru.otus.services.UserAuthServiceImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithFilterBasedSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws Exception {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();


        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, User.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);

        var userTemplate = new DataTemplateHibernate<>(User.class);
        DBServiceUser userService = new DbServiceUserImpl(transactionManager, userTemplate);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userService);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, userService, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
