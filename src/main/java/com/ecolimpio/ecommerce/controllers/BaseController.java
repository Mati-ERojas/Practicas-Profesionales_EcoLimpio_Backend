package com.ecolimpio.ecommerce.controllers;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecolimpio.ecommerce.models.entities.BaseEntity;
import com.ecolimpio.ecommerce.services.BaseService;

public abstract class BaseController<E extends BaseEntity, ID extends Serializable> {
    protected BaseService<E, ID> service;

    public BaseController(BaseService<E, ID> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<E> getById(@PathVariable ID id) throws Exception {
        return service.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<E>> get() throws Exception {
        List<E> entities = service.get();
        return ResponseEntity.ok(entities);
    }

    @PostMapping()
    public ResponseEntity<E> create(@RequestBody E entity) throws Exception {
        E createdEntity = service.create(entity);
        return ResponseEntity.ok(createdEntity);
    }

    @PutMapping()
    public ResponseEntity<E> update(@RequestBody E entity) throws Exception {
        E updatedEntity = service.update(entity);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ID id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok("Entidad con id: " + id + " eliminada con éxito.");
    }

    @PatchMapping("/toggle-habilitado/{id}")
    public ResponseEntity<String> toggleHabilitado(@PathVariable ID id) throws Exception {
        service.disableOrEnable(id);
        return ResponseEntity.ok("Estado 'habilitado' alternado correctamente.");
    }

    @GetMapping("/getEnabled")
    public ResponseEntity<List<E>> getEnabled() throws Exception {
        List<E> enabledEntities = service.getAllEnableEntities();
        return ResponseEntity.ok(enabledEntities);
    }
}
