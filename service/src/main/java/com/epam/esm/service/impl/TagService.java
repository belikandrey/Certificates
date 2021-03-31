package com.epam.esm.service.impl;

import com.epam.esm.dao.AbstractDAO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ValidatorException;
import com.epam.esm.service.EntityService;
import com.epam.esm.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TagService implements EntityService<Tag> {
    private Validator<Tag> validator;
    private AbstractDAO<Tag> tagDao;

    @Autowired
    public TagService(Validator<Tag> validator, AbstractDAO<Tag> tagDao) {
        this.validator = validator;
        this.tagDao = tagDao;
    }

    @Override
    public Collection<Tag> readAll() {
        return tagDao.readAll();
    }

    @Override
    public Tag read(int id) {
        return tagDao.read(id);
    }

    @Override
    public Tag create(Tag tag) throws ValidatorException {
        validator.validate(tag);
        tagDao.create(tag);
        return tag;
    }

    @Override
    public void update(int id, Tag tag) throws ValidatorException {
        validator.validate(tag);
        tagDao.update(id, tag);
    }

    @Override
    public void delete(int id) {
        tagDao.delete(id);
    }
}
