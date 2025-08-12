package com.ecolimpio.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.ecolimpio.ecommerce.models.entities.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, String> {

}
