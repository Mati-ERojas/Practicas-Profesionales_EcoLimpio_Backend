package com.ecolimpio.ecommerce.models.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.models.entities.enums.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaConDetallesDTO {
    private String id;
    private Integer recibo;
    private LocalDateTime fecha;
    private Estado estado;
    private Float total;
    private Usuario vendedor;
    private List<DetalleVentaDTO> detalles;
}