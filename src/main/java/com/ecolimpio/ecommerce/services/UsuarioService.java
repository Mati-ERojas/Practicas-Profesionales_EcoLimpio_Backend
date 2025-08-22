package com.ecolimpio.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.repositories.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario, String> {
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }
}
