package com.ecolimpio.ecommerce.models.entities;

import com.ecolimpio.ecommerce.models.entities.enums.Estado;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ventas")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta extends BaseEntity {
    private Integer recibo;
    private String fecha;
    private Estado estado;
    private Float total;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "cierreCaja_id")
    private CierreCaja cierreCaja;
}
