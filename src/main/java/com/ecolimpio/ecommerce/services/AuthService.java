package com.ecolimpio.ecommerce.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecolimpio.ecommerce.models.AuthResponse;
import com.ecolimpio.ecommerce.models.LoginRequest;
import com.ecolimpio.ecommerce.models.RegisterRequest;
import com.ecolimpio.ecommerce.models.entities.Usuario;
import com.ecolimpio.ecommerce.models.entities.enums.Rol;
import com.ecolimpio.ecommerce.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public boolean validarToken(String token) {
        return !jwtService.isTokenExpired(token);
    }

    public AuthResponse registerAdmin(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .nombre(request.getNombre())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.ADMIN)
                .build();

        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

    public AuthResponse registerEmpleado(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .nombre(request.getNombre())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.VENTAS)
                .build();

        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
