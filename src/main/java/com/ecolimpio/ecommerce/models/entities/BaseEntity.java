package com.ecolimpio.ecommerce.models.entities;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public abstract class BaseEntity implements Serializable {
    @Id
    protected String id;

    protected boolean habilitado = true;

    @PrePersist
    public void generateId() {
        if (id == null)
            id = UUID.randomUUID().toString();
    }
}
