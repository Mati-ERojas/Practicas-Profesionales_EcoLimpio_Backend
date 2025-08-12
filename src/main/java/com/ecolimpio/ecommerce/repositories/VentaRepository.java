package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Venta;

@Repository
public interface VentaRepository extends BaseRepository<Venta, String> {
    List<Venta> findAllByVendedorId(String idVendedor);

    List<Venta> findAllByCierreCajaId(String idCierreCaja);
}
