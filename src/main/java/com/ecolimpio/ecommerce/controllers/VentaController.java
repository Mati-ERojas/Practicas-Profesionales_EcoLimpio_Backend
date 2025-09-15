package com.ecolimpio.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.entities.CierreCaja;
import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.models.entities.Venta;
import com.ecolimpio.ecommerce.services.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController extends BaseController<Venta, String> {
    public VentaController(VentaService ventaService) {
        super(ventaService);
    }

    @Autowired
    public VentaService ventaService;

    @GetMapping("/abiertas")
    public ResponseEntity<List<Venta>> listarVentasAbiertas() {
        List<Venta> ventas = ventaService.listarVentasAbiertas();
        return ResponseEntity.ok(ventas);
    }

    @PostMapping("/usuarios/{idVenta}")
    public Optional<Venta> agregarVendedor(@RequestBody Usuario usuario, @PathVariable String id) throws Exception {
        Venta venta = ventaService.agregarVendedor(id, usuario);
        return Optional.ofNullable(venta);
    }

    @PostMapping("/cierres-caja/{idVenta}")
    public Optional<Venta> agregarCierreCaja(@RequestBody CierreCaja cierreCaja, @PathVariable String id)
            throws Exception {
        Venta venta = ventaService.agregarCierreCaja(id, cierreCaja);
        return Optional.ofNullable(venta);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<Venta>> listarPorVendedor(@PathVariable String id) throws Exception {
        List<Venta> ventas = ventaService.listarPorVendedor(id);
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/cierres-caja/{id}")
    public ResponseEntity<List<Venta>> listarPorCierreCaja(@PathVariable String id) throws Exception {
        List<Venta> ventas = ventaService.listarPorCierreCaja(id);
        return ResponseEntity.ok(ventas);
    }
}
