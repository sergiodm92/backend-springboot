package com.ordernow.api.modules.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank(message = "Email or username cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    // Constructor por defecto
    public LoginDTO() {
    }

    // Constructor con parámetros
    public LoginDTO(String emailOrUsername, String password) {
        this.email = emailOrUsername;
        this.password = password;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String emailOrUsername) {
        this.email = emailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
