package org.example.javachat.service;

import org.example.javachat.model.ChatRoom;
import org.example.javachat.model.User;
import org.example.javachat.repository.ChatRoomRepository;
import org.example.javachat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ChatRoomService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public void joinRoom(Long userId, Long roomId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);

        if (optionalUser.isPresent() && optionalChatRoom.isPresent()) {
            User user = optionalUser.get();
            ChatRoom room = optionalChatRoom.get();

            room.getUsers().add(user);
            chatRoomRepository.save(room);
        }
    }

    public void leaveRoom(Long userId, Long roomId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);

        if (optionalUser.isPresent() && optionalChatRoom.isPresent()) {
            User user = optionalUser.get();
            ChatRoom room = optionalChatRoom.get();

            room.getUsers().remove(user);
            chatRoomRepository.save(room);
        }
    }
}

