package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Venta;
import com.ecolimpio.ecommerce.models.entities.enums.Estado;

@Repository
public interface VentaRepository extends BaseRepository<Venta, String> {
    List<Venta> findAllByVendedorId(String idVendedor);

    List<Venta> findAllByCierreCajaId(String idCierreCaja);

    @Query("SELECT COALESCE(MAX(v.recibo), 999) FROM Venta v")
    Integer findMaxRecibo();

    List<Venta> findByEstado(Estado estado);
}
