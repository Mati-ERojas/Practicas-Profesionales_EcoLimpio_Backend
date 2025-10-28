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
            SELECT p
            FROM Producto p
            WHERE p.categoria.id = :idCategoria
            AND p.stock > 0
            """)
    List<Producto> findByCategoriaAndStock(@Param("idCategoria") String idCategoria);


    @Query("""
            SELECT DISTINCT p
            FROM Producto p
            WHERE
                p.habilitado = true AND p.stock > 0
            """)
    List<Producto> findByEnabled();

    @Query("""
                SELECT DISTINCT p
                FROM Producto p
                JOIN p.categoria c
                WHERE
                    (:search IS NULL OR
                        LOWER(p.sku) LIKE LOWER(CONCAT('%', :search, '%')) OR
                        LOWER(p.titulo) LIKE LOWER(CONCAT('%', :search, '%')) OR
                        LOWER(p.marca) LIKE LOWER(CONCAT('%', :search, '%')) OR
                        LOWER(c.nombre) LIKE LOWER(CONCAT('%', :search, '%')))
                AND p.habilitado = true
                AND p.stock > 0
            """)
    List<Producto> findBySearch(@Param("search") String search);

}
