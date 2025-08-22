package com.ecolimpio.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.Categoria;
import com.ecolimpio.ecommerce.models.entities.Producto;
import com.ecolimpio.ecommerce.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService extends BaseService<Producto, String> {
    public ProductoService(ProductoRepository productoRepository) {
        super(productoRepository);
    }

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Producto agregarCategoria(String idProducto, Categoria categoria) throws Exception {
        try {
            Producto producto = productoRepository.findById(idProducto).orElse(null);
            if (producto != null) {
                producto.setCategoria(categoria);
                productoRepository.save(producto);
                return producto;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<Producto> listarPorCategoria(String idCategoria) throws Exception {
        try {
            return productoRepository.findAllByCategoriaId(idCategoria);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
