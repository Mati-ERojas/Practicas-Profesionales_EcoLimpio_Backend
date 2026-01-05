package com.ecolimpio.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.Categoria;
import com.ecolimpio.ecommerce.repositories.CategoriaRepository;
import com.ecolimpio.ecommerce.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService extends BaseService<Categoria, String> {
    public CategoriaService(CategoriaRepository categoriaRepository) {
        super(categoriaRepository);
    }

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public void delete(String id) throws Exception {
        try {
            // Desasociar productos de la categoría
            productoRepository.desasociarCategoria(id);
            // Eliminar categoría
            super.delete(id);
        } catch (Exception e) {
            throw new Exception("Error al eliminar categoría: " + e.getMessage());
        }
    }
}
