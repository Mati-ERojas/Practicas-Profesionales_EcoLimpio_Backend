package com.ecolimpio.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.dtos.CierreCajaConVentasDTO;
import com.ecolimpio.ecommerce.models.entities.CierreCaja;
import com.ecolimpio.ecommerce.services.CierreCajaService;

@RestController
@RequestMapping("/cierres-caja")
public class CierreCajaController extends BaseController<CierreCaja, String> {
    public CierreCajaController(CierreCajaService cierreCajaService) {
        super(cierreCajaService);
    }

    @Autowired
    private CierreCajaService cierreCajaService;

    @GetMapping("/con-ventas")
    public ResponseEntity<List<CierreCajaConVentasDTO>> getCierresConVentas() throws Exception {
        List<CierreCajaConVentasDTO> cierresCaja = cierreCajaService.getCierresConVentas();
        return ResponseEntity.ok(cierresCaja);
    }
}
