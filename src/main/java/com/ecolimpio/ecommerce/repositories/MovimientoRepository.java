package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Movimiento;
import com.ecolimpio.ecommerce.models.entities.enums.TipoMovimiento;

@Repository
public interface MovimientoRepository extends BaseRepository<Movimiento, String> {
    List<Movimiento> findAllByUsuarioId(String idUsuario);

    List<Movimiento> findAllByProductoId(String idProducto);

    /*
     * To do - Listar movimientos por:
     * Usuario, fechaMin y fechaMax, tipo de movimiento, SKU, nombre producto,
     * cantidad
     * producto, precio del producto, precio total del movimiento.
     */
    @Query("""
                SELECT DISTINCT m
                FROM Movimiento m
                JOIN m.producto p
                WHERE (:idUsuario IS NULL OR m.usuario.id = :idUsuario)
                    AND (:tipoMovimiento IS NULL OR m.tipo = :tipoMovimiento)
                    AND (:sku IS NULL OR p.sku = :sku)
                    AND (:nombreProducto IS NULL OR p.titulo = :nombreProducto)
                    AND (:cantidadProducto IS NULL OR m.cantidad = :cantidadProducto)
                    AND (:totalMovimiento IS NULL OR m.total = :totalMovimiento)
                    AND (:fechaMin IS NULL OR m.fecha >= :fechaMin)
                    AND (:fechaMax IS NULL OR m.fecha <= :fechaMax)
                    AND (:precioProducto IS NULL OR
                        (CASE WHEN m.tipo = com.ecolimpio.ecommerce.models.entities.enums.TipoMovimiento.VENTA
                            THEN p.precioVenta
                            ELSE p.precioCompra END) = :precioProducto)
            """)
    List<Movimiento> findAllByParams(@Param("idUsuario") String idUsuario,
            @Param("tipoMovimiento") TipoMovimiento tipoMovimiento,
            @Param("sku") String sku,
            @Param("nombreProducto") String nombreProducto,
            @Param("cantidadProducto") Integer cantidadProducto,
            @Param("totalMovimiento") Float totalMovimiento,
            @Param("fechaMin") String fechaMin,
            @Param("fechaMax") String fechaMax,
            @Param("precioProducto") Float precioProducto);
}
