package com.ordernow.api.modules.roles.controllers;

import com.ordernow.api.modules.roles.dto.CreateRoleDTO;
import com.ordernow.api.modules.roles.entities.Role;
import com.ordernow.api.modules.roles.services.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles") 
public class RolesController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create") 
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleDTO role) {

        return ResponseEntity.ok(roleService.createRole(role.getName(), role.getDescription()));
    }

    @GetMapping("/") 
    public List<Role> getRoles() {

        return roleService.getAllRoles();
    }

}
