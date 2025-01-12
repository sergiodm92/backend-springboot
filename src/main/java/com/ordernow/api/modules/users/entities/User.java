package com.ordernow.api.modules.users.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ordernow.api.modules.auth.entities.UserAuth;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    private String urlImage;

    @OneToOne
    @JoinColumn(name = "user_auth_id", referencedColumnName = "id")
    @JsonManagedReference
    private UserAuth userAuth;
}
