package ru.otus.dao.core.repository;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class DataTemplateHibernate<T> implements DataTemplate<T> {

    private final Class<T> clazz;

    public DataTemplateHibernate(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<T> findById(Session session, long id) {
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<T> findAll(Session session) {
        return session.createQuery(String.format("from %s", clazz.getSimpleName()), clazz).getResultList();
    }

    @Override
    public void insert(Session session, T object) {
        session.persist(object);
    }

    @Override
    public void update(Session session, T object) {
        session.merge(object);
    }

    @Override
    public Optional<T> findByField(Session session, String fieldName, String fieldValue) {
        String query = String.format("from %s where %s = '%s'", clazz.getSimpleName(), fieldName, fieldValue);
        return Optional.ofNullable(session.createQuery(query, clazz).getSingleResult());
    }
}
