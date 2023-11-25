package com.example.teamxx_password_hasher.controller;

import com.example.teamxx_password_hasher.model.User;
import com.example.teamxx_password_hasher.service.PasswordService;
import com.example.teamxx_password_hasher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/{username}")
    public String getUserPassword(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return passwordService.decryptPassword(user.getEncryptedPassword());
        } else {
            return "User not found";
        }
    }

    @PostMapping("/check")
    public boolean checkPassword(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        return user != null && passwordService.checkPassword(password, user.getEncryptedPassword());
    }
}

