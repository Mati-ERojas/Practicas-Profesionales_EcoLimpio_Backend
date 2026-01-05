package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Movimiento;
import com.ecolimpio.ecommerce.models.entities.enums.TipoMovimiento;

@Repository
public interface MovimientoRepository extends BaseRepository<Movimiento, String> {
    List<Movimiento> findAllByUsuarioId(String idUsuario);

    List<Movimiento> findAllByProductoId(String idProducto);

    @Query("""
                SELECT DISTINCT m
                FROM Movimiento m
                LEFT JOIN m.producto p
                WHERE (:idUsuario IS NULL OR m.usuario.id = :idUsuario)
                    AND (:skuNombre IS NULL OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :skuNombre, '%'))
                        OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :skuNombre, '%')))
                    AND (:tipoMovimiento IS NULL OR m.tipo = :tipoMovimiento)
                    AND m.total <= COALESCE(:totalMovMax, m.total)
                    AND m.total >= COALESCE(:totalMovMin, m.total)
                    AND (:fechaMin IS NULL OR m.fecha >= :fechaMin)
                    AND (:fechaMax IS NULL OR m.fecha <= :fechaMax)
            """)
    List<Movimiento> findAllByParams(@Param("idUsuario") String idUsuario,
            @Param("tipoMovimiento") TipoMovimiento tipoMovimiento,
            @Param("skuNombre") String skuNombre,
            @Param("totalMovMax") Float totalMovMax,
            @Param("totalMovMin") Float totalMovMin,
            @Param("fechaMin") LocalDateTime fechaMin,
            @Param("fechaMax") LocalDateTime fechaMax);

    @Modifying
    @Query("DELETE FROM Movimiento m WHERE m.producto.id = :productoId")
    void deleteAllByProductoId(@Param("productoId") String productoId);

    @Modifying
    @Query("""
            UPDATE Movimiento m
            SET m.usuario = NULL
            WHERE m.usuario.id = :usuarioId
            """)
    void desasociarUsuario(@Param("usuarioId") String usuarioId);
}
