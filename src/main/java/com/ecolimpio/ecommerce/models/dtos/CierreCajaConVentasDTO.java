package com.ecolimpio.ecommerce.models.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaConVentasDTO {
    private String id;
    private String codigoCierre;
    private LocalDateTime fechaHora;
    private Float total;
    private List<VentaConDetallesDTO> ventas;
}
