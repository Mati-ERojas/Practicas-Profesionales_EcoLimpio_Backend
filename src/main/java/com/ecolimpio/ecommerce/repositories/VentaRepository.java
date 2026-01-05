package com.ecolimpio.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query("UPDATE Venta v SET v.cierreCaja = null WHERE v.cierreCaja.id = :cierreCajaId")
    void desasociarCierreCaja(@Param("cierreCajaId") String cierreCajaId);

    @Modifying
    @Query("UPDATE Venta v SET v.vendedor = null WHERE v.vendedor.id = :vendedorId")
    void desasociarVendedor(@Param("vendedorId") String vendedorId);
}
