package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Producto;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, String> {
    List<Producto> findAllByCategoriaId(String idCategoria);
}
