package com.ordernow.api.modules.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank(message = "Email or username cannot be empty")
    private String emailOrUsername;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    // Constructor por defecto
    public LoginDTO() {
    }

    // Constructor con parámetros
    public LoginDTO(String emailOrUsername, String password) {
        this.emailOrUsername = emailOrUsername;
        this.password = password;
    }

    // Getters y Setters
    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
