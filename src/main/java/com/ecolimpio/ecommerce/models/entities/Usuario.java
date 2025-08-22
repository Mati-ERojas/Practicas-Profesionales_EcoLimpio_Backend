package com.ecolimpio.ecommerce.models.entities;

import com.ecolimpio.ecommerce.models.entities.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario extends BaseEntity {
    @Column(unique = true)
    private String email;
    private String nombre;
    private String password;
    private Rol rol;
    private String fechaCreacion;
}
