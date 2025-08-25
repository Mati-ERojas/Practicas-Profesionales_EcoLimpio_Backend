package com.ecolimpio.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.entities.CierreCaja;
import com.ecolimpio.ecommerce.services.CierreCajaService;

@RestController
@RequestMapping("/cierres-caja")
public class CierreCajaController extends BaseController<CierreCaja, String> {
    public CierreCajaController(CierreCajaService cierreCajaService) {
        super(cierreCajaService);
    }
}
