package com.example.teamxx_password_hasher.service;


import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final String SECRET_KEY = generateSecretKey();

    private static String generateSecretKey() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] key = new byte[16];
            secureRandom.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);
        } catch (Exception e) {
            throw new RuntimeException("Error generating AES secret key", e);
        }
    }

    @Override
    public String encryptPassword(String password) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SECRET_KEY.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    @Override
    public String decryptPassword(String encryptedPassword) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SECRET_KEY.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }

    @Override
    public boolean checkPassword(String rawPassword, String encryptedPassword) {
        return rawPassword.equals(decryptPassword(encryptedPassword));
    }
}
