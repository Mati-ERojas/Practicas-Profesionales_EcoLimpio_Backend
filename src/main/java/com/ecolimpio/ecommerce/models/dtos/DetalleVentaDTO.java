package com.ecolimpio.ecommerce.models.dtos;

import com.ecolimpio.ecommerce.models.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private String id;
    private Producto producto;
    private Integer cantidad;
    private Float subtotal;
}
