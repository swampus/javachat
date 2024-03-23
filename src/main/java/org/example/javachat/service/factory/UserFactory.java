package org.example.javachat.service.factory;

import org.example.javachat.model.User;
import org.example.javachat.service.JavaChatPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    @Autowired
    private JavaChatPasswordEncoder passwordEncoder;

    public User createUser(String username, String password) {
        var user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}
