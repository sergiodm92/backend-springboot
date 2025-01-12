package com.ordernow.api.modules.roles.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateRoleDTO {
    
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Email cannot be empty")
    private String description;
    @NotBlank(message = "Password cannot be empty")
    private String[] permissions;
    
    public CreateRoleDTO() {
    }
    public CreateRoleDTO(String name, String description, String[] permissions) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String[] getPermissions() {
        return permissions;
    }
    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
