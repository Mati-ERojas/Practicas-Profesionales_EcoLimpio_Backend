package com.ecolimpio.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.entities.CierreCaja;
import com.ecolimpio.ecommerce.repositories.CierreCajaRepository;

@Service
public class CierreCajaService extends BaseService<CierreCaja, String> {
    public CierreCajaService(CierreCajaRepository cierreCajaRepository) {
        super(cierreCajaRepository);
    }
}
