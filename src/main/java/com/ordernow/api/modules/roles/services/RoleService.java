package com.ordernow.api.modules.roles.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordernow.api.modules.roles.entities.Role;
import com.ordernow.api.modules.roles.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(String name, String description) {
        if (roleRepository.existsByName(name)) {
            throw new IllegalArgumentException("Role already exists");
        }
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
