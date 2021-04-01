package com.epam.esm.dao;

import com.epam.esm.entity.Entity;

import java.util.Collection;

public interface AbstractDAO<T extends Entity> {
    Collection<T> readAll();

    Collection<T> readAll(int id);

    T read(int id);

    void create(T t);

    void update(int id, T t);

    void delete(int id);
}
