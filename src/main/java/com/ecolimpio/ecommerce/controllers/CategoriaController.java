package com.ecolimpio.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.entities.Categoria;
import com.ecolimpio.ecommerce.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<Categoria, String> {
    public CategoriaController(CategoriaService categoriaService) {
        super(categoriaService);
    }
}
