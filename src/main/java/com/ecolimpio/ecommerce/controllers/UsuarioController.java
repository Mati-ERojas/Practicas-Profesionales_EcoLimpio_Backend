package com.ecolimpio.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController<Usuario, String> {
    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
    }
}
