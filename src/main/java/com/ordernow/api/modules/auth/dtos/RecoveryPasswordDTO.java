package com.ordernow.api.modules.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public class RecoveryPasswordDTO {

    @NotBlank(message = "Email cannot be empty")
    private String email;

    public RecoveryPasswordDTO() {
    }

    public RecoveryPasswordDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
