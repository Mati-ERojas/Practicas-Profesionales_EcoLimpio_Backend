package com.ecolimpio.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.entities.Categoria;
import com.ecolimpio.ecommerce.models.entities.Producto;
import com.ecolimpio.ecommerce.services.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/productos")
public class ProductoController extends BaseController<Producto, String> {
    public ProductoController(ProductoService productoService) {
        super(productoService);
    }

    @Autowired
    public ProductoService productoService;

    @PostMapping("/categorias/{idProducto}")
    public Optional<Producto> agregarCategoria(@RequestBody Categoria categoria, @PathVariable String id)
            throws Exception {
        Producto producto = productoService.agregarCategoria(id, categoria);
        return Optional.ofNullable(producto);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable String id) throws Exception {
        List<Producto> productos = productoService.listarPorCategoria(id);
        return ResponseEntity.ok(productos);
    }
}
