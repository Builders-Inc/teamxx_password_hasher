package com.example.teamxx_password_hasher.service;

import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    String encryptPassword(String password);
    String decryptPassword(String encryptedPassword);
    boolean checkPassword(String rawPassword, String encryptedPassword);
}

