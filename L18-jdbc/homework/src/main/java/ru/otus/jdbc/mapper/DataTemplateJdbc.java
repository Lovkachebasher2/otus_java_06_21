package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.executor.DbExecutor;
import ru.otus.exception.BaseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData<T> entitySQLMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(
                connection,
                entitySQLMetaData.getSelectByIdSql(),
                Collections.singletonList(id),
                resultSet -> {
                    try {
                        if (resultSet.next()) {
                            T entity = createInstance();
                            setFields(resultSet, entity, entitySQLMetaData.getMetaData().getAllFields());
                            return entity;
                        }
                        return null;
                    } catch (SQLException e) {
                        throw new BaseException(e);
                    }
                }
        );
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(
                connection,
                entitySQLMetaData.getSelectAllSql(),
                Collections.emptyList(),
                resultSet -> {
                    List<T> result = new ArrayList<>();
                    try {
                        while (resultSet.next()) {
                            T entity = createInstance();
                            setFields(resultSet, entity, entitySQLMetaData.getMetaData().getAllFields());
                            result.add(entity);
                        }
                        return result;
                    } catch (Exception e) {
                        throw new BaseException(e);
                    }
                }).orElseThrow(() -> new BaseException("What is it? It's nothing here"));
    }

    @Override
    public long insert(Connection connection, T entity) {
        return dbExecutor.executeStatement(
                connection,
                entitySQLMetaData.getInsertSql(),
              getInsertParameters(entity)
        );
    }

    @Override
    public void update(Connection connection, T entity) {
        dbExecutor.executeStatement(
                connection,
                entitySQLMetaData.getUpdateSql(),
                getUpdateParameters(entity));
    }

    private T createInstance() {
        final Constructor<T> constructor = entitySQLMetaData.getMetaData().getConstructor();
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BaseException(e);
        }
    }


    private void setFields(ResultSet rs, T entity, List<Field> fields) {
        for (Field field : fields) {
            setFieldValue(entity, field, rs);
        }
    }

    private void setFieldValue(T entity, Field field, ResultSet rs) {
        field.setAccessible(true);
        try {
            field.set(entity, rs.getObject(field.getName()));
        } catch (SQLException | IllegalAccessException e) {
            throw new BaseException(e);
        }
    }

    private List<Object> getInsertParameters(T entity) {
        List<Object> parameterList = new ArrayList<>();
        entitySQLMetaData.getMetaData().getFieldsWithoutId().forEach(
                field -> parameterList.add(getFieldValue(entity, field))
        );
        return parameterList;
    }

    private Object getFieldValue(T entity, Field field) {
        try {
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new BaseException(e);
        }
    }

    private List<Object> getUpdateParameters(T enttiy) {
        List<Object> parameterList = new ArrayList<>();
        for (Field field : entitySQLMetaData.getMetaData().getFieldsWithoutId()) {
            parameterList.add(getFieldValue(enttiy, field));
        }
        parameterList.add(getFieldValue(enttiy, entitySQLMetaData.getMetaData().getIdField()));
        return parameterList;
    }

}

