package com.ordernow.api.modules.users.controllers;

import com.ordernow.api.modules.users.entities.User;
import com.ordernow.api.modules.users.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") 
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all") 
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @PutMapping("/id/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/id/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
