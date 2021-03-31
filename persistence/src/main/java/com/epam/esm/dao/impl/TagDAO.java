package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDAO;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TagDAO implements AbstractDAO<Tag> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<Tag> readAll() {
        return jdbcTemplate.query("SELECT * FROM tag", new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag read(int id) {
        return jdbcTemplate.query("SELECT * FROM tag WHERE id = ?", new BeanPropertyRowMapper<>(Tag.class), id)
                .stream().findAny().orElse(null);

    }

    @Override
    public void create(Tag tag) {
        jdbcTemplate.update("INSERT INTO tag() VALUE(?)", tag.getName());
    }

    @Override
    public void update(int id, Tag tag) {
        jdbcTemplate.update("UPDATE tag SET name = ? where id=?", tag.getName(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM tag WHERE id=?", id);
    }
}
