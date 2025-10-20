package com.ecolimpio.ecommerce.services;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.Movimiento;
import com.ecolimpio.ecommerce.models.entities.Producto;
import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.models.entities.enums.TipoMovimiento;
import com.ecolimpio.ecommerce.repositories.MovimientoRepository;

import jakarta.transaction.Transactional;

@Service
public class MovimientoService extends BaseService<Movimiento, String> {
    public MovimientoService(MovimientoRepository movimientoRepository) {
        super(movimientoRepository);
    }

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional
    public Movimiento agregarUsuario(String idMovimiento, Usuario usuario) throws Exception {
        try {
            Movimiento movimiento = movimientoRepository.findById(idMovimiento).orElse(null);
            if (movimiento != null) {
                movimiento.setUsuario(usuario);
                movimientoRepository.save(movimiento);
                return movimiento;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Movimiento agregarProducto(String idMovimiento, Producto producto) throws Exception {
        try {
            Movimiento movimiento = movimientoRepository.findById(idMovimiento).orElse(null);
            if (movimiento != null) {
                movimiento.setProducto(producto);
                movimientoRepository.save(movimiento);
                return movimiento;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Movimiento> listarPorUsuario(String idUsuario) throws Exception {
        try {
            return movimientoRepository.findAllByUsuarioId(idUsuario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Movimiento> listarPorProducto(String idProducto) throws Exception {
        try {
            return movimientoRepository.findAllByProductoId(idProducto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Movimiento> listarPorParametros(
            String idUsuario,
            TipoMovimiento tipoMovimiento,
            String skuNombre,
            Float totalMovMax,
            Float totalMovMin,
            LocalDateTime fechaMin,
            LocalDateTime fechaMax) throws Exception {
        try {
            return movimientoRepository.findAllByParams(idUsuario, tipoMovimiento, skuNombre,
                    totalMovMax, totalMovMin, fechaMin, fechaMax);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
