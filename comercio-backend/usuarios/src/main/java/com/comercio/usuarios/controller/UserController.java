package com.comercio.usuarios.controller;

import com.comercio.usuarios.dto.LoginRequest;
import com.comercio.usuarios.model.User;
import com.comercio.usuarios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Usuario eliminado con éxito";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        System.out.println("Intento de login para: " + request.getUsername());
        boolean loginSuccess = userService.loginUser(request.getUsername(), request.getPassword());
        return loginSuccess ? "Login exitoso" : "Credenciales inválidas";
    }
}