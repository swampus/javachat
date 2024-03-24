package org.example.javachat.service;

import org.example.javachat.exception.AuthFailedException;
import org.example.javachat.model.User;
import org.example.javachat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    private JavaChatPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new AuthFailedException("Inccorrect username or password!");
    }
}
