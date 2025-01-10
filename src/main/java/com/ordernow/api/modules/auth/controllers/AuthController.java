package com.ordernow.api.modules.auth.controllers;

import com.ordernow.api.modules.auth.services.AuthService;
import com.ordernow.api.modules.auth.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // Define un prefijo para las rutas de este controlador
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register") // Ruta para registrar un usuario
    public ResponseEntity<String> register(@RequestBody User user) {
        // Llamamos al servicio de autenticación para registrar al usuario
        String token = authService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok("User registered successfully. Token: " + token);
    }

    @PostMapping("/login") // Ruta para hacer login
    public ResponseEntity<String> login(@RequestBody User user) {
        // Llamamos al servicio de autenticación para autenticar al usuario
        String token = authService.authenticateUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok("Login successful. Token: " + token);
    }

    @PostMapping("/logout") // Ruta para cerrar sesión
    public ResponseEntity<String> logout() {
        // Llamamos al servicio de autenticación para cerrar sesión
        authService.logout();
        return ResponseEntity.ok("User logged out successfully");
    }
}
