package com.example.homework.metadata;

public interface EntitySQLMetaData<T> {
    String getSelectAllSql();

    String getSelectByIdSql();

    String getInsertSql();

    String getUpdateSql();

    EntityClassMetaData<T> getMetadata();
}
