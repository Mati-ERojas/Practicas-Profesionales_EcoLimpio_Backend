package com.ecolimpio.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.Categoria;
import com.ecolimpio.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService extends BaseService<Categoria, String> {
    public CategoriaService(CategoriaRepository categoriaRepository) {
        super(categoriaRepository);
    }
}
