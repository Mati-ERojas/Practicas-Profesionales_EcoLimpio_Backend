package com.ecolimpio.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService extends BaseService<Usuario, String> {

    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario create(Usuario usuario) throws Exception {
        try {
            Usuario newUser = Usuario.builder()
                    .email(usuario.getEmail())
                    .nombre(usuario.getNombre())
                    .password(passwordEncoder.encode(usuario.getPassword()))
                    .rol(usuario.getRol())
                    .build();
            return usuarioRepository.save(newUser);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
public Usuario update(Usuario usuario) throws Exception {
    try {
        Usuario oldUser = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        // Actualizar campos editables
        oldUser.setEmail(usuario.getEmail());
        oldUser.setNombre(usuario.getNombre());
        oldUser.setRol(usuario.getRol());

        // Actualizar password solo si viene una nueva
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            oldUser.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }

        return usuarioRepository.save(oldUser);
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }
}


    @Transactional
    public Usuario findByEmail(String email) throws Exception {
        try {
            return usuarioRepository.findByEmail(email).orElse(null);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
