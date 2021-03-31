package com.epam.esm.service.impl;

import com.epam.esm.dao.AbstractDAO;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.ValidatorException;
import com.epam.esm.service.EntityService;
import com.epam.esm.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class CertificateService implements EntityService<Certificate> {
    private Validator<Certificate> validator;
    private AbstractDAO<Certificate> certificateDAO;

    @Autowired
    public CertificateService(Validator<Certificate> validator, AbstractDAO<Certificate> certificateDAO) {
        this.validator = validator;
        this.certificateDAO = certificateDAO;
    }

    @Override
    public Collection<Certificate> readAll() {
        return certificateDAO.readAll();
    }

    @Override
    public Certificate read(int id) {
        return certificateDAO.read(id);
    }

    @Override
    public Certificate create(Certificate giftCertificate) throws ValidatorException {
        giftCertificate.setCreateDate(LocalDateTime.now());
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        validator.validate(giftCertificate);
        certificateDAO.create(giftCertificate);
        return giftCertificate;
    }

    @Override
    public void update(int id, Certificate giftCertificate) throws ValidatorException {
        giftCertificate.setLastUpdateDate(LocalDateTime.now());
        validator.validate(giftCertificate);
        certificateDAO.update(id, giftCertificate);
    }

    @Override
    public void delete(int id) {
        certificateDAO.delete(id);
    }
}
