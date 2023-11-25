package com.example.teamxx_password_hasher.service;

import com.example.teamxx_password_hasher.model.User;
import org.springframework.stereotype.Service;

public interface PasswordService {

    String encryptPassword(String password);

}

