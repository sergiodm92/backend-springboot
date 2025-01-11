package com.ordernow.api.modules.auth.repositories;

import com.ordernow.api.modules.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    // Método para buscar un usuario por nombre de usuario
    User findByUsername(String username);

    // Método para buscar un usuario por correo electrónico
    User findByEmail(String email);

    // Método para verificar si un usuario con un nombre de usuario ya existe
    boolean existsByUsername(String username);

    // Método para verificar si un usuario con un correo electrónico ya existe
    boolean existsByEmail(String email);

    // Si quieres realizar búsquedas personalizadas adicionales, puedes agregar más métodos aquí
    // Por ejemplo: encontrar usuarios por una parte de su nombre de usuario
    // List<User> findByUsernameContaining(String partialUsername);
}
