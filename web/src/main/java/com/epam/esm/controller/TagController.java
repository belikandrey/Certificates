package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ValidatorException;
import com.epam.esm.service.impl.CertificateService;
import com.epam.esm.service.impl.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final CertificateService certificateService;

    @Autowired
    public TagController(TagService tagService, CertificateService certificateService) {
        this.tagService = tagService;
        this.certificateService = certificateService;
    }

    @GetMapping()
    public ResponseEntity<?> readAll() {
        final Collection<Tag> tags = tagService.readAll();
        return tags != null && !tags.isEmpty() ?
                new ResponseEntity<>(tags, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable("id") int id) {
        final Tag tag = tagService.read(id);
        return tag != null && tag.getName() != null ?
                new ResponseEntity<>(tag, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody Tag tag) {
        try {
            tagService.create(tag);
            return new ResponseEntity<>(tag, HttpStatus.OK);
        } catch (ValidatorException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Tag tag) {
        try {
            tagService.update(id, tag);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ValidatorException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}