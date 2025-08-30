package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register new user with hashed password
    public void register(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    // Authenticate user login
    public User login(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(hashPassword(rawPassword)))
                .orElse(null);
    }

    // Hashing utility (MD5 - for demo only)
    private String hashPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    // Update user profile
    public User updateUserProfile(User user) {
        Optional<User> existingUserOpt = userRepository.findById(user.getId());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            
            if (!existingUser.getPassword().equals(user.getPassword())) {
                user.setPassword(hashPassword(user.getPassword()));
            }

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with ID: " + user.getId());
        }
    }

    // Get user by ID
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }
}
