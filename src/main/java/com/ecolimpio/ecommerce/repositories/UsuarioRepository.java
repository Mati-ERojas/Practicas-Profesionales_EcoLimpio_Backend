package com.ecolimpio.ecommerce.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
