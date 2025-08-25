package com.ecolimpio.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolimpio.ecommerce.models.AuthResponse;
import com.ecolimpio.ecommerce.models.LoginRequest;
import com.ecolimpio.ecommerce.models.RegisterRequest;
import com.ecolimpio.ecommerce.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthResponse> registerAdmin(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerAdmin(request));
    }

    @PostMapping("/registerEmpleado")
    public ResponseEntity<AuthResponse> registerEmpleado(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerEmpleado(request));
    }

    @GetMapping("/validarToken")
    public ResponseEntity<Boolean> validarToken(@RequestParam("token") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.ok(false);
        }
        boolean esValido = authService.validarToken(token);
        return ResponseEntity.ok(esValido);
    }
}
