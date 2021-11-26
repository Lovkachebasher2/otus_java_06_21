package ru.otus.jdbc.mapper;

import ru.otus.Id;
import ru.otus.exception.BaseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData {
    private Class<T> tClass;

    public EntityClassMetaDataImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public String getName() {
        return tClass.getSimpleName();
    }

    @Override
    public Constructor getConstructor() {
        try {
            return tClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new BaseException("oops construcror not found");
        }
    }

    @Override
    public Field getIdField() {
        for (var field : tClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                return field;
            }
        }
        throw new BaseException("class haven't field with annotation 'id'");
    }

    @Override
    public List<Field> getAllFields() {
        return List.of(tClass.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> listField = new ArrayList<>();
        for (var field : tClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Id.class)) {
                listField.add(field);
            }
        }
        return listField;
    }
}
