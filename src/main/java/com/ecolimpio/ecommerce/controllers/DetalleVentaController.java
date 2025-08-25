package com.ecolimpio.ecommerce.controllers;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.entities.DetalleVenta;
import com.ecolimpio.ecommerce.models.entities.Producto;
import com.ecolimpio.ecommerce.models.entities.Venta;
import com.ecolimpio.ecommerce.services.DetalleVentaService;

@RestController
@RequestMapping("/detalles-ventas")
public class DetalleVentaController extends BaseController<DetalleVenta, String> {
    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        super(detalleVentaService);
    }

    @Autowired
    public DetalleVentaService detalleVentaService;

    @PostMapping("/ventas/{idDetalleVenta}")
    public Optional<DetalleVenta> agregarVenta(@RequestBody Venta venta, @PathVariable String idDetalleVenta)
            throws Exception {
        DetalleVenta detalleVenta = detalleVentaService.agregarVenta(idDetalleVenta, venta);
        return Optional.ofNullable(detalleVenta);
    }

    @PostMapping("/productos/{idDetalleVenta}")
    public Optional<DetalleVenta> agregarProducto(@RequestBody Producto producto, @PathVariable String idDetalleVenta)
            throws Exception {
        DetalleVenta detalleVenta = detalleVentaService.agregarProducto(idDetalleVenta, producto);
        return Optional.ofNullable(detalleVenta);
    }

    @GetMapping("/ventas/{id}")
    public ResponseEntity<List<DetalleVenta>> listarPorVenta(@PathVariable String id) throws Exception {
        List<DetalleVenta> listaDetallesVenta = detalleVentaService.listarPorVenta(id);
        return ResponseEntity.ok(listaDetallesVenta);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<List<DetalleVenta>> listarPorProducto(@PathVariable String id) throws Exception {
        List<DetalleVenta> listaDetallesVenta = detalleVentaService.listarPorProducto(id);
        return ResponseEntity.ok(listaDetallesVenta);
    }
}
