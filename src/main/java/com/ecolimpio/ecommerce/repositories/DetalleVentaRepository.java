package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.DetalleVenta;

@Repository
public interface DetalleVentaRepository extends BaseRepository<DetalleVenta, String> {
    List<DetalleVenta> findAllByVentaId(String idVenta);

    List<DetalleVenta> findAllByProductoId(String idProducto);
}
