package com.example.homework.metadata;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public interface EntityClassMetaData<T> {


    String getName();

    Constructor<T> getConstructor();

    //Поле Id должно определять по наличию аннотации Id
    //Аннотацию @Id надо сделать самостоятельно
    Field getIdField();

    List<Field> getAllFields();

    List<Field> getFieldsWithoutId();
}
