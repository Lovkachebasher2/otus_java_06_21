package com.example;

import com.example.cachehw.HwListener;
import com.example.cachehw.MyCache;
import com.example.core.repository.DataTemplate;
import com.example.core.repository.DataTemplateHibernate;
import com.example.core.repository.HibernateUtils;
import com.example.core.sessionmanager.TransactionManager;
import com.example.core.sessionmanager.TransactionManagerHibernate;
import com.example.crm.dbmigrations.MigrationsExecutorFlyway;
import com.example.crm.model.Client;
import com.example.crm.service.DbServiceClientImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

class DbServiceClientImplTest {

    private static Logger log = LoggerFactory.getLogger(DbServiceClientImplTest.class);
    private static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private static final Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
    ;
    private static SessionFactory sessionFactory;
    private static TransactionManager transactionManager;
    private static DataTemplate<Client> clientTemplate;


    @BeforeAll
    public static void setUp() {
        new MigrationsExecutorFlyway(configuration).executeMigrations();
        sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class);
        transactionManager = new TransactionManagerHibernate(sessionFactory);
        clientTemplate = new DataTemplateHibernate<>(Client.class);
    }

    @Test
    void saveClientWithCache() {
        var cache = new MyCache<Long, Client>();
        var listener = new HwListener<Long, Client>() {
            @Override
            public void notify(Long key, Client value, String action) {
                log.info("key:{}, value:{}, action: {}", key, value, action);
            }
        };

        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate, cache);
        cache.addListener(listener);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Client client = new Client(UUID.randomUUID().toString());
            long id = dbServiceClient.saveClient(client).getId();
            dbServiceClient.getClient(id);
        }

        long end = System.currentTimeMillis();
        log.info("time spend: " + (end - start) + ".ml");
        log.info("Size is:" + cache.cacheSize());
        cache.removeListener(listener);
    }

    @Test
    void saveClientWithoutCache() {
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate, null);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Client client = new Client(UUID.randomUUID().toString());
            long id = dbServiceClient.saveClient(client).getId();
            dbServiceClient.getClient(id);
        }

        long end = System.currentTimeMillis();
        log.info("time spend: " + (end - start) + ".ml");
    }
}