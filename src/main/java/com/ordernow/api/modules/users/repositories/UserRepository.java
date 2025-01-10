package com.ordernow.api.modules.users.repositories;

import com.ordernow.api.modules.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Método para encontrar un usuario por su nombre de usuario (username)
    Optional<User> findByUsername(String username);
    
    // Método para encontrar un usuario por su correo electrónico
    Optional<User> findByEmail(String email);
    
    // Método para verificar si un usuario existe por su nombre de usuario
    boolean existsByUsername(String username);
    
    // Método para verificar si un usuario existe por su correo electrónico
    boolean existsByEmail(String email);
    
    // Método para eliminar un usuario por su nombre de usuario
    void deleteByUsername(String username);
    
    // Método para eliminar un usuario por su correo electrónico
    void deleteByEmail(String email);
    
    // Método para encontrar un usuario por su id
    Optional<User> findById(Long id);
}