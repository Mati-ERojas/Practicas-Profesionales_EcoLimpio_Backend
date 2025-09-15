package com.ecolimpio.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.CierreCaja;
import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.models.entities.Venta;
import com.ecolimpio.ecommerce.models.entities.enums.Estado;
import com.ecolimpio.ecommerce.repositories.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService extends BaseService<Venta, String> {
    public VentaService(VentaRepository ventaRepository) {
        super(ventaRepository);
    }

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    @Transactional
    public Venta create(Venta venta) {
        if (venta.getRecibo() == null) {
            Integer ultimoRecibo = ventaRepository.findMaxRecibo();
            venta.setRecibo(ultimoRecibo + 1);
        }
        return ventaRepository.save(venta);
    }

    @Transactional
    public List<Venta> listarVentasAbiertas() {
        return ventaRepository.findByEstado(Estado.ABIERTO);
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
}
