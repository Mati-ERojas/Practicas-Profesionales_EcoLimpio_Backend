package com.ecolimpio.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Categoria;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria, String> {

}
