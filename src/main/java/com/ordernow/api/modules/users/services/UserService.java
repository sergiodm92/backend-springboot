package com.ordernow.api.modules.users.services;

import com.ordernow.api.modules.auth.entities.User;
import com.ordernow.api.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Buscar usuario por nombre de usuario
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Buscar usuario por correo electrónico
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Guardar un nuevo usuario
    public void save(User user) {
        userRepository.save(user);
    }

    // Otros métodos relacionados con usuarios según sea necesario
}
