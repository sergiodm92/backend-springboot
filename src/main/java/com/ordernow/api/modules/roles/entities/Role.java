package com.ordernow.api.modules.roles.entities;

import java.util.Set;

import com.ordernow.api.modules.auth.entities.UserAuth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Ejemplo: "ADMIN", "USER", "MODERATOR"

    @Column(nullable = false)
    private String description; // Ejemplo: "Administrador del sistema"

    @ManyToMany(mappedBy = "roles")
    private Set<UserAuth> users; // Relación con usuarios
}
