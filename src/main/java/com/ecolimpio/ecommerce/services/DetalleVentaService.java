package com.ecolimpio.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.DetalleVenta;
import com.ecolimpio.ecommerce.models.entities.Producto;
import com.ecolimpio.ecommerce.models.entities.Venta;
import com.ecolimpio.ecommerce.repositories.DetalleVentaRepository;

import jakarta.transaction.Transactional;

@Service
public class DetalleVentaService extends BaseService<DetalleVenta, String> {
    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        super(detalleVentaRepository);
    }

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Transactional
    public DetalleVenta agregarVenta(String idDetalleVenta, Venta venta) throws Exception {
        try {
            DetalleVenta detalleVenta = detalleVentaRepository.findById(idDetalleVenta).orElse(null);
            if (detalleVenta != null) {
                detalleVenta.setVenta(venta);
                detalleVentaRepository.save(detalleVenta);
                return detalleVenta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public DetalleVenta agregarProducto(String idDetalleVenta, Producto producto) throws Exception {
        try {
            DetalleVenta detalleVenta = detalleVentaRepository.findById(idDetalleVenta).orElse(null);
            if (detalleVenta != null) {
                detalleVenta.setProducto(producto);
                detalleVentaRepository.save(detalleVenta);
                return detalleVenta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<DetalleVenta> listarPorVenta(String idVenta) throws Exception {
        try {
            return detalleVentaRepository.findAllByVentaId(idVenta);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<DetalleVenta> listarPorProducto(String idProducto) throws Exception {
        try {
            return detalleVentaRepository.findAllByProductoId(idProducto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
