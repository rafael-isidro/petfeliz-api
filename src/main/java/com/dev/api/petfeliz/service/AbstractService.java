package com.dev.api.petfeliz.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.beans.BeanUtils.copyProperties;

public abstract class AbstractService<E, ID, R extends JpaRepository<E, ID>> {

    private final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }
    public boolean exists(E entity) {
        return this.repository.exists(Example.of(entity));
    }
    public boolean existsById(ID id) {
        return this.repository.existsById(id);
    }

    public E save(E entity) {
        if (this.exists(entity)) {
            throw new EntityExistsException();
        }

        return this.repository.save(entity);
    }

    public List<E> findAll() { return this.repository.findAll(); }

    public Optional<E> findById(ID id) { return this.repository.findById(id); }

    public E update(ID id, E entity) {
        if (!this.existsById(id)) {
            throw new EntityNotFoundException();
        }

        E existingEntity = this.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        copyProperties(entity, existingEntity, "id");

        return this.repository.save(existingEntity);
    }

    public void delete(E entity) { this.repository.delete(entity); }

    public void deleteById(ID id) { this.repository.deleteById(id); }
}
