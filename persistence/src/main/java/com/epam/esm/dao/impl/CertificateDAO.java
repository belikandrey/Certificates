package com.epam.esm.dao.impl;

import com.epam.esm.dao.AbstractDAO;
import com.epam.esm.dao.mapper.CertificateMapper;
import com.epam.esm.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CertificateDAO implements AbstractDAO<Certificate> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Collection<Certificate> readAll() {
        return jdbcTemplate.query("SELECT * FROM certificate", new CertificateMapper());
    }

    @Override
    public Certificate read(int id) {
        return jdbcTemplate.query("SELECT * FROM certificate WHERE id=?", new CertificateMapper(), id)
                .stream().findAny().orElse(null);
    }

    @Override
    public void create(Certificate certificate) {
        jdbcTemplate.update("INSERT INTO certificate" +
                "(name, description, price, duration, create_date, last_update_date)" +
                " values(?, ?, ?, ?, ?, ?)", certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(), certificate.getCreateDate(), certificate.getLastUpdateDate());
    }

    @Override
    public void update(int id, Certificate certificate) {
        jdbcTemplate.update("UPDATE certificate SET " +
                "name=?, description=?, price=?, duration=?, create_date=?," +
                " last_update_date=? where id=?", certificate.getName(),
                certificate.getDescription(), certificate.getPrice(),
                certificate.getDuration(), certificate.getCreateDate(),
                certificate.getLastUpdateDate(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM certificate WHERE id=?", id);
    }
}
