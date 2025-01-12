package com.ordernow.api.modules.users.services;

import com.ordernow.api.modules.users.entities.User;
import com.ordernow.api.modules.users.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Buscar usuario por correo electrónico
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Guardar un nuevo usuario
    public void save(User user) {
        userRepository.save(user);
    }

    // Actualizar un usuario
    public void updateUser(Long id, User user) {
        userRepository.findById(id).ifPresentOrElse(
                u -> userRepository.save(user),
                () -> userRepository.deleteById(id)
        );
    }

    // Eliminar un usuario
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
