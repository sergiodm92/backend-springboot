package com.ordernow.api.modules.auth.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ordernow.api.modules.roles.entities.Role;
import com.ordernow.api.modules.users.entities.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_auth")
@Data
@NoArgsConstructor
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "userAuth")
    private Set<Role> roles; // Relación con usuarios

    @OneToOne(mappedBy = "userAuth")
    @JsonBackReference
    private User user;

}
