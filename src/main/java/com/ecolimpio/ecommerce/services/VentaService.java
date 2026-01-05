package com.ecolimpio.ecommerce.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.dtos.DetalleVentaDTO;
import com.ecolimpio.ecommerce.models.dtos.VentaConDetallesDTO;
import com.ecolimpio.ecommerce.models.entities.CierreCaja;
import com.ecolimpio.ecommerce.models.entities.DetalleVenta;
import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.models.entities.Venta;
import com.ecolimpio.ecommerce.models.entities.enums.Estado;
import com.ecolimpio.ecommerce.repositories.DetalleVentaRepository;
import com.ecolimpio.ecommerce.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService extends BaseService<Venta, String> {
    public VentaService(VentaRepository ventaRepository) {
        super(ventaRepository);
    }

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    @Transactional
    public Venta create(Venta venta) {
        if (venta.getRecibo() == null) {
            Integer ultimoRecibo = ventaRepository.findMaxRecibo();
            venta.setRecibo(ultimoRecibo + 1);
        }
        venta.setEstado(Estado.ABIERTO);
        return ventaRepository.save(venta);
    }

    @Transactional
    public VentaConDetallesDTO getVentaDTOById(String id) throws Exception {
        try {
            List<DetalleVenta> detalles = detalleVentaRepository.findAllByVentaId(id);

            List<DetalleVentaDTO> detalleDTOs = detalles.stream().map(d -> DetalleVentaDTO.builder()
                    .id(d.getId())
                    .producto(d.getProducto())
                    .cantidad(d.getCantidad())
                    .subtotal(d.getSubtotal())
                    .build()).toList();

            Venta venta = ventaRepository.findById(id).orElse(null);

            VentaConDetallesDTO ventaDTO = VentaConDetallesDTO.builder()
                    .id(venta.getId())
                    .recibo(venta.getRecibo())
                    .fecha(venta.getFecha())
                    .estado(venta.getEstado())
                    .total(venta.getTotal())
                    .vendedor(venta.getVendedor())
                    .detalles(detalleDTOs)
                    .build();

            return ventaDTO;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<VentaConDetallesDTO> listarVentasAbiertas() throws Exception {
        try {
            List<DetalleVenta> detalles = detalleVentaRepository.findDetallesByVentaAbierta();

            // Agrupar detalles por venta
            Map<Venta, List<DetalleVenta>> detallesPorVenta = detalles.stream()
                    .collect(Collectors.groupingBy(DetalleVenta::getVenta));

            // Transformar a DTOs
            return detallesPorVenta.entrySet().stream().map(entry -> {
                Venta venta = entry.getKey();
                List<DetalleVenta> detallesVenta = entry.getValue();

                // Mapear detalles a DTOs
                List<DetalleVentaDTO> detallesDTO = detallesVenta.stream().map(d -> {
                    DetalleVentaDTO dto = new DetalleVentaDTO();
                    dto.setId(d.getId());
                    dto.setProducto(d.getProducto());
                    dto.setCantidad(d.getCantidad());
                    dto.setSubtotal(d.getSubtotal());
                    return dto;
                }).toList();

                // Mapear venta con sus detalles
                VentaConDetallesDTO ventaDTO = new VentaConDetallesDTO();
                ventaDTO.setId(venta.getId());
                ventaDTO.setRecibo(venta.getRecibo());
                ventaDTO.setFecha(venta.getFecha());
                ventaDTO.setEstado(venta.getEstado());
                ventaDTO.setTotal(venta.getTotal());
                ventaDTO.setVendedor(venta.getVendedor());
                ventaDTO.setDetalles(detallesDTO);

                return ventaDTO;
            }).toList();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Venta agregarVendedor(String idVenta, Usuario usuario) throws Exception {
        try {
            Venta venta = ventaRepository.findById(idVenta).orElse(null);
            if (venta != null) {
                venta.setVendedor(usuario);
                ventaRepository.save(venta);
                return venta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Venta agregarCierreCaja(String idVenta, CierreCaja cierreCaja) throws Exception {
        try {
            Venta venta = ventaRepository.findById(idVenta).orElse(null);
            if (venta != null) {
                venta.setCierreCaja(cierreCaja);
                venta.setEstado(com.ecolimpio.ecommerce.models.entities.enums.Estado.CERRADO);
                ventaRepository.save(venta);
                return venta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Venta> listarPorVendedor(String idUsuario) throws Exception {
        try {
            return ventaRepository.findAllByVendedorId(idUsuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Venta> listarPorCierreCaja(String idCierreCaja) throws Exception {
        try {
            return ventaRepository.findAllByCierreCajaId(idCierreCaja);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(String id) {
        detalleVentaRepository.deleteAllByVentaId(id);
        ventaRepository.deleteById(id);
    }
}
