package com.ecolimpio.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.dtos.CierreCajaConVentasDTO;
import com.ecolimpio.ecommerce.models.dtos.DetalleVentaDTO;
import com.ecolimpio.ecommerce.models.dtos.VentaConDetallesDTO;
import com.ecolimpio.ecommerce.models.entities.CierreCaja;
import com.ecolimpio.ecommerce.models.entities.DetalleVenta;
import com.ecolimpio.ecommerce.models.entities.Venta;
import com.ecolimpio.ecommerce.repositories.CierreCajaRepository;
import com.ecolimpio.ecommerce.repositories.DetalleVentaRepository;
import com.ecolimpio.ecommerce.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class CierreCajaService extends BaseService<CierreCaja, String> {
    public CierreCajaService(CierreCajaRepository cierreCajaRepository) {
        super(cierreCajaRepository);
    }

    @Autowired
    private CierreCajaRepository cierreCajaRepository;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Transactional
    public List<CierreCajaConVentasDTO> getCierresConVentas() throws Exception {
        try {
            List<CierreCaja> cierres = cierreCajaRepository.findAll();

            return cierres.stream().map(cierre -> {
                // Buscar ventas
                List<Venta> ventas = ventaRepository.findAllByCierreCajaId(cierre.getId());

                // Mapear las ventas y sus detalles
                List<VentaConDetallesDTO> ventaDTOs = ventas.stream().map(v -> {
                    List<DetalleVenta> detalles = detalleVentaRepository.findAllByVentaId(v.getId());

                    List<DetalleVentaDTO> detalleDTOs = detalles.stream().map(d -> DetalleVentaDTO.builder()
                            .id(d.getId())
                            .producto(d.getProducto())
                            .cantidad(d.getCantidad())
                            .subtotal(d.getSubtotal())
                            .build()).toList();

                    return VentaConDetallesDTO.builder()
                            .id(v.getId())
                            .recibo(v.getRecibo())
                            .fecha(v.getFecha())
                            .estado(v.getEstado())
                            .total(v.getTotal())
                            .vendedor(v.getVendedor())
                            .detalles(detalleDTOs)
                            .build();
                }).toList();

                // Mapear el cierre con sus ventas
                return CierreCajaConVentasDTO.builder()
                        .id(cierre.getId())
                        .codigoCierre(cierre.getCodigoCierre())
                        .fechaHora(cierre.getFechaHora())
                        .total(cierre.getTotal())
                        .ventas(ventaDTOs)
                        .build();
            }).toList();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String id) throws Exception {
        try {
            // Desasociar ventas del cierre de caja
            ventaRepository.desasociarCierreCaja(id);
            // Eliminar cierre de caja
            super.delete(id);
        } catch (Exception e) {
            throw new Exception("Error al eliminar cierre de caja: " + e.getMessage());
        }
    }
}
