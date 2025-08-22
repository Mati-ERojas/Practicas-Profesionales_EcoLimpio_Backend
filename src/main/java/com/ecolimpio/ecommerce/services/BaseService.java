package com.ecolimpio.ecommerce.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.BaseEntity;
import com.ecolimpio.ecommerce.repositories.BaseRepository;

import jakarta.transaction.Transactional;

@Service
public abstract class BaseService<E extends BaseEntity, ID extends Serializable> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseService(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Transactional
    public Optional<E> getById(ID id) throws Exception {
        try {
            return Optional.ofNullable(baseRepository.findById(id).orElse(null));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<E> get() throws Exception {
        try {
            return baseRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E create(E entity) throws Exception {
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E update(E entity) throws Exception {
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void delete(ID id) throws Exception {
        try {
            baseRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void disableOrEnable(ID id) throws Exception {
        try {
            Optional<E> optionalEntity = baseRepository.findById(id);
            if (optionalEntity.isPresent()) {
                E entity = optionalEntity.get();
                entity.setHabilitado(!entity.isHabilitado());
                baseRepository.save(entity);
            } else {
                throw new Exception("No existe una entidad con la id: " + id);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<E> getAllEnableEntities() throws Exception {
        try {
            return baseRepository.findAll().stream().filter(BaseEntity::isHabilitado).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
