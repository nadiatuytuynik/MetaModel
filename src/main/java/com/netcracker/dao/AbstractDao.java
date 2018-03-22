package com.netcracker.dao;

public interface AbstractDao<T,ID>{
    T create(T entity);

    T findOne(ID id);
}
