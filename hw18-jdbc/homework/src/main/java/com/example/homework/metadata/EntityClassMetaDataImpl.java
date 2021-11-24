package com.example.homework.metadata;

import com.example.homework.annotations.Id;
import com.example.homework.exception.BaseException;
import com.example.homework.exception.MetaDataException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData {
    private final Class<T> tClass;

    public EntityClassMetaDataImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public String getName() {
        return tClass.getSimpleName().toLowerCase();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return tClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new BaseException("No id field");
        }
    }

    @Override
    public Field getIdField() {
        for (Field field : tClass.getDeclaredFields()) {
            if (isId(field)) {
                return field;
            }
        }
        throw new BaseException("No id field");
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(tClass.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return Stream.of(tClass.getDeclaredFields())
                .filter(f -> !isId(f))
                .collect(Collectors.toList());
    }

    private boolean isId(Field field) {
        return field.getAnnotationsByType(Id.class).length > 0;
    }
}
