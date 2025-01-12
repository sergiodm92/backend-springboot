package com.ordernow.api.modules.auth.controllers;

import com.ordernow.api.modules.auth.services.AuthService;
import com.ordernow.api.modules.auth.dtos.RegisterDTO;
import com.ordernow.api.modules.auth.entities.UserAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") 
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register") 
    public ResponseEntity<String> register(@RequestBody RegisterDTO user) {
        // Llamamos al servicio de autenticación para registrar al usuario
        String token = authService.registerUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName());
        return ResponseEntity.ok("User registered successfully. Token: " + token);
    }

    @PostMapping("/login") 
    public ResponseEntity<String> login(@RequestBody UserAuth user) {
        // Llamamos al servicio de autenticación para autenticar al usuario
        String token = authService.authenticateUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok("Login successful. Token: " + token);
    }

    @PostMapping("/logout") 
    public ResponseEntity<String> logout() {
        // Llamamos al servicio de autenticación para cerrar sesión
        authService.logout();
        return ResponseEntity.ok("User logged out successfully");
    }
}
