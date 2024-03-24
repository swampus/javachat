package org.example.javachat.service;

import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.model.ChatRoom;
import org.example.javachat.model.Message;
import org.example.javachat.model.User;
import org.example.javachat.repository.ChatRoomRepository;
import org.example.javachat.repository.MessageRepository;
import org.example.javachat.repository.UserRepository;
import org.example.javachat.service.factory.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MessageService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageFactory messageFactory;

    @Transactional
    public List<Message> fetchMessages(Long userId, Long roomId) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);
        if (optionalChatRoom.isPresent()) {
            ChatRoom room = optionalChatRoom.get();
            if (room.getUsers().stream().noneMatch(t -> t.getUserId().equals(userId))) {
                throw new MiscommunicationException("#E66: user not in room");
            }
            return room.getMessages();
        }
        return Collections.emptyList();
    }

    public void sendMessage(Long userId, Long roomId, String content) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(roomId);

        if (optionalUser.isPresent() && optionalChatRoom.isPresent()) {
            User user = optionalUser.get();
            ChatRoom room = optionalChatRoom.get();

            var message = messageFactory.createMessage(user, room, content);
            messageRepository.save(message);
        }
    }

    public void deleteMessage(Long messageId, Long userId) {
        var optMsg = messageRepository.findById(messageId);
        optMsg.ifPresent(t -> {
            if (!userId.equals(t.getSender().getUserId())) {
                throw new MiscommunicationException("#E55");
            }
            messageRepository.delete(t);
        });
    }

    public Optional<Message> getById(Long messageId) {
        return messageRepository.findById(messageId);
    }

}
