package com.ecolimpio.ecommerce.models.entities;

import com.ecolimpio.ecommerce.models.entities.enums.TipoMovimiento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimientos")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento extends BaseEntity {
    private String fecha;
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    // Indica la cantidad agregada o sustraida, del stock, de un producto en el
    // movimiento
    private Integer cantidad;

    /*
     * Se indica el costo o ingreso en un movimiento según la cantidad del producto
     * y el tipo de movimiento.
     * Ej: Si el movimiento es un ajuste, el total se calcula con el precio de
     * compra del producto.
     * Al contrario, si el movimiento es una venta, el total se calcula con el
     * precio de venta del producto.
     */
    private Float total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
