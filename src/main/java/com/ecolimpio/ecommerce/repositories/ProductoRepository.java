package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Producto;

@Repository
public interface ProductoRepository extends BaseRepository<Producto, String> {
    List<Producto> findAllByCategoriaId(String idCategoria);

    @Query("""
                SELECT DISTINCT p
                FROM Producto p
                JOIN p.categoria c
                WHERE :search IS NULL
                    OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :search, '%'))
                    OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :search, '%'))
                    OR LOWER(p.marca) LIKE LOWER(CONCAT('%', :search, '%'))
                    OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
            """)
    List<Producto> findBySearch(@Param("search") String search);
}
