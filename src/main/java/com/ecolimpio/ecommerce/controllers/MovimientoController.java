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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.entities.Movimiento;
import com.ecolimpio.ecommerce.models.entities.Producto;
import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.models.entities.enums.TipoMovimiento;
import com.ecolimpio.ecommerce.services.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController extends BaseController<Movimiento, String> {
    public MovimientoController(MovimientoService movimientoService) {
        super(movimientoService);
    }

    @Autowired
    public MovimientoService movimientoService;

    @PostMapping("/usuarios/{idMovimiento}")
    public Optional<Movimiento> agregarUsuario(@RequestBody Usuario usuario, @PathVariable String id) throws Exception {
        Movimiento movimiento = movimientoService.agregarUsuario(id, usuario);
        return Optional.ofNullable(movimiento);
    }

    @PostMapping("/productos/{idMovimiento}")
    public Optional<Movimiento> agregarProducto(@RequestBody Producto producto, @PathVariable String id)
            throws Exception {
        Movimiento movimiento = movimientoService.agregarProducto(id, producto);
        return Optional.ofNullable(movimiento);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<Movimiento>> listarPorUsuario(@PathVariable String id) throws Exception {
        List<Movimiento> listaMovimientos = movimientoService.listarPorUsuario(id);
        return ResponseEntity.ok(listaMovimientos);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<List<Movimiento>> listarPorProducto(@PathVariable String id) throws Exception {
        List<Movimiento> listaMovimientos = movimientoService.listarPorProducto(id);
        return ResponseEntity.ok(listaMovimientos);
    }

    @GetMapping("/filtro-movimientos")
    public ResponseEntity<List<Movimiento>> filtrarPorParametros(
            @RequestParam(required = false) String idUsuario,
            @RequestParam(required = false) String tipoMovimiento,
            @RequestParam(required = false) String skuNombre,
            @RequestParam(required = false) String totalMovimientoMin,
            @RequestParam(required = false) String totalMovimientoMax,
            @RequestParam(required = false) String fechaMin,
            @RequestParam(required = false) String fechaMax)
            throws Exception {
        TipoMovimiento tipoMov = (tipoMovimiento == null || tipoMovimiento.equalsIgnoreCase("null")
                ? null
                : TipoMovimiento.valueOf(tipoMovimiento.toUpperCase()));
        Float totalMovMin = (totalMovimientoMin == null || totalMovimientoMin.equalsIgnoreCase("null")
                ? null
                : Float.parseFloat(totalMovimientoMin));
        Float totalMovMax = (totalMovimientoMax == null || totalMovimientoMax.equalsIgnoreCase("null")
                ? null
                : Float.parseFloat(totalMovimientoMax));

        List<Movimiento> movimientos = movimientoService.listarPorParametros(idUsuario, tipoMov, skuNombre,
                totalMovMax, totalMovMin, fechaMin, fechaMax);
        return ResponseEntity.ok(movimientos);
    }
}
