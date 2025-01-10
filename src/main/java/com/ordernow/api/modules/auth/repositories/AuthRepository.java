package com.ordernow.api.modules.auth.repositories;

import com.ordernow.api.modules.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    // Método para buscar un usuario por nombre de usuario
    Optional<User> findByUsername(String username);

    // Método para buscar un usuario por correo electrónico
    Optional<User> findByEmail(String email);

    // Método para verificar si un usuario con un nombre de usuario ya existe
    boolean existsByUsername(String username);

    // Método para verificar si un usuario con un correo electrónico ya existe
    boolean existsByEmail(String email);

    // Método para buscar un usuario por su ID
    Optional<User> findById(Long id);

    // Si necesitas buscar usuarios por roles (esto depende de tu modelo de roles)
    // List<User> findByRolesContains(String role);

    // También puedes agregar métodos para búsquedas personalizadas
    // Ejemplo: encontrar usuarios que tengan una parte del nombre en su nombre de usuario
    // List<User> findByUsernameContaining(String partialUsername);

    // Métodos adicionales como actualización o eliminación de usuarios pueden ser manejados por JpaRepository

}
