package com.epam.esm.service;

import com.epam.esm.entity.Entity;
import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.exception.ValidatorException;

import java.util.Collection;

public interface EntityService<T extends Entity> {
    Collection<T> readAll();
    T read(int id);
    T create(T t) throws ValidatorException;
    void update(int id, T t) throws ValidatorException;
    void delete(int id);
}
