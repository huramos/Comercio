package com.comercio.usuarios.service;

import com.comercio.usuarios.model.User;
import com.comercio.usuarios.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate; // Solo para enviar mensajes

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
            throw new IllegalArgumentException("Username, password, and role are required");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User savedUser = userRepository.save(user);
        String message = "Usuario " + user.getUsername() + " ha sido registrado";
        try {
            rabbitTemplate.convertAndSend("login-exchange", "login.event", message);
            System.out.println("Mensaje enviado a RabbitMQ: " + message);
        } catch (Exception e) {
            System.err.println("Error enviando mensaje a RabbitMQ: " + e.getMessage());
        }
        return savedUser;
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(updatedUser.getUsername());
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    public boolean loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                String message = "Usuario " + username + " ha iniciado sesión";
                try {
                    rabbitTemplate.convertAndSend("login-exchange", "login.event", message);
                    System.out.println("Mensaje enviado a RabbitMQ: " + message);
                } catch (Exception e) {
                    System.err.println("Error enviando mensaje a RabbitMQ: " + e.getMessage());
                }
                return true;
            }
        }
        return false;
    }
}