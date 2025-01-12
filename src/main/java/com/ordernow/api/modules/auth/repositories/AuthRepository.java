package com.ordernow.api.modules.auth.repositories;

import com.ordernow.api.modules.auth.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserAuth, Long> {

    // Método para buscar un usuario por correo electrónico
    Optional<UserAuth> findByEmail(String email);

    // Método para verificar si un usuario con un nombre de usuario ya existe
    boolean existsByUsername(String username);

    // Método para verificar si un usuario con un correo electrónico ya existe
    boolean existsByEmail(String email);

    // Puedes agregar búsquedas personalizadas adicionales aquí
}
