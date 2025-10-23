package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.DetalleVenta;

@Repository
public interface DetalleVentaRepository extends BaseRepository<DetalleVenta, String> {
    List<DetalleVenta> findAllByVentaId(String idVenta);

    List<DetalleVenta> findAllByProductoId(String idProducto);

    @Query("""
            SELECT d
            FROM DetalleVenta d
            JOIN FETCH d.venta v
            JOIN FETCH d.producto p
            JOIN FETCH v.vendedor u
            WHERE v.estado = com.ecolimpio.ecommerce.models.entities.enums.Estado.ABIERTO
            """)
    List<DetalleVenta> findDetallesByVentaAbierta();
}
