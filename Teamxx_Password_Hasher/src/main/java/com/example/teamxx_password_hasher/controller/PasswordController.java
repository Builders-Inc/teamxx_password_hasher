package com.example.teamxx_password_hasher.controller;

import com.example.teamxx_password_hasher.model.User;
import com.example.teamxx_password_hasher.repository.UserRepository;
import com.example.teamxx_password_hasher.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class PasswordController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        String encryptedPassword = passwordService.encryptPassword(user.getEncryptedPassword());
        user.setEncryptedPassword(encryptedPassword);
        userRepository.save(user);
    }

}

