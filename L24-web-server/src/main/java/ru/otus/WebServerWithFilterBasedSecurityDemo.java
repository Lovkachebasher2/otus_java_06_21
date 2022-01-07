package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.cfg.Configuration;
import ru.otus.dao.core.repository.DataTemplateHibernate;
import ru.otus.dao.core.repository.HibernateUtils;
import ru.otus.dao.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.dao.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.dao.crm.model.Address;
import ru.otus.dao.crm.model.Client;
import ru.otus.dao.crm.model.Phone;
import ru.otus.dao.crm.model.User;
import ru.otus.dao.crm.services.client.DBServiceClient;
import ru.otus.dao.crm.services.client.DBServiceClientImpl;
import ru.otus.dao.crm.services.user.DBServiceUser;
import ru.otus.dao.crm.services.user.DBServiceUserImpl;
import ru.otus.server.ClientsWebServer;
import ru.otus.server.ClientsWebServerWithFilterBasedSecurity;
import ru.otus.dao.crm.services.TemplateProcessor;
import ru.otus.dao.crm.services.TemplateProcessorImpl;
import ru.otus.dao.crm.services.user.UserAuthService;
import ru.otus.dao.crm.services.user.UserAuthServiceImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница клиентов
    http://localhost:8080/clients

    // REST сервис
    http://localhost:8080/api/clients/3
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


        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class, Phone.class, User.class);


        var transactionManager = new TransactionManagerHibernate(sessionFactory);

        var userTemplate = new DataTemplateHibernate<>(User.class);
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
        DBServiceUser userService = new DBServiceUserImpl(transactionManager, userTemplate);
        DBServiceClient clientService = new DBServiceClientImpl(transactionManager, clientTemplate);
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userService);

        ClientsWebServer clientsWebServer = new ClientsWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, clientService, gson, templateProcessor);

        clientsWebServer.start();
        clientsWebServer.join();
    }
}
