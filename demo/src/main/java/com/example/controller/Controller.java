package com.example.controller;

import com.example.model.User;
import com.example.model.UserRepository;

public class Controller {

    private final UserRepository userRepository = new UserRepository();

    public User getUserByUsername(String username) {

        return userRepository.getUserByUsername(username);
    }

    public boolean createUser(String username, String email, String password) {

        if (username.length() < 30 && email.length() < 50 && password.length() < 72)
            return userRepository.createUser(username, email, password);

        
        return false;
    }
}
