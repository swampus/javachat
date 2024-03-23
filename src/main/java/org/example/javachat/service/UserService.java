package org.example.javachat.service;

import org.example.javachat.exception.InvalidPasswordException;
import org.example.javachat.model.User;
import org.example.javachat.repository.UserRepository;
import org.example.javachat.service.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private JavaChatPasswordEncoder passwordEncoder;


}
