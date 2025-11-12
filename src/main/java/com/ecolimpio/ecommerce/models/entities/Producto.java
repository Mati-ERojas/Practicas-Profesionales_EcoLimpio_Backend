package com.ecolimpio.ecommerce.models.entities;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntity {
    @Column(unique = true)
    private String sku;
    private String titulo;
    private Float precioCompra;
    private Float precioVenta;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String marca;
    private Integer stock;
    private Integer porcentajeOferta;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Manejo de imagen (Cloudinary):

    // Campo temporal en donde se guardará el archivo de la imagen:
    @Transient
    private MultipartFile archivo;
    // Campo en el que se guardará la id en Cloudinary de la imagen:
    @Column(name = "public_id")
    private String publicId;
    // Campo en el que se guardará la url en Cloudinary de la imagen:
    private String urlImagen;
}
