package org.example.javachat.service;

import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.model.ChatRoom;
import org.example.javachat.model.Message;
import org.example.javachat.model.User;
import org.example.javachat.repository.ChatRoomRepository;
import org.example.javachat.repository.MessageRepository;
import org.example.javachat.repository.UserRepository;
import org.example.javachat.service.factory.MessageFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageServiceUnitTest {

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageFactory messageFactory;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchMessages_UserNotInRoom() {
        Long userId = 1L;
        Long roomId = 1L;
        ChatRoom room = new ChatRoom();
        room.setRoomId(roomId);
        room.setUsers(Collections.emptySet());
        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.of(room));

        assertThrows(MiscommunicationException.class, () -> messageService.fetchMessages(userId, roomId));
        verify(chatRoomRepository).findById(roomId);
    }

    @Test
    public void testFetchMessages_UserInRoom() {
        Long userId = 1L;
        Long roomId = 1L;
        User user = new User();
        user.setUserId(userId);
        ChatRoom room = new ChatRoom();
        room.setRoomId(roomId);
        room.setUsers(new HashSet<>());
        room.getUsers().add(user);
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        room.setMessages(messages);
        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.of(room));

        List<Message> fetchedMessages = messageService.fetchMessages(userId, roomId);

        assertEquals(messages, fetchedMessages);
        verify(chatRoomRepository).findById(roomId);
    }

    @Test
    public void testSendMessage_UserAndRoomExists() {
        Long userId = 1L;
        Long roomId = 1L;
        String content = "Test message";
        User user = new User();
        ChatRoom room = new ChatRoom();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.of(room));
        when(messageFactory.createMessage(user, room, content)).thenReturn(new Message());
        messageService.sendMessage(userId, roomId, content);

        verify(messageFactory).createMessage(user, room, content);
        verify(messageRepository).save(any(Message.class));
    }

    @Test
    public void testDeleteMessage_MessageExistsAndUserIsSender() {
        Long messageId = 1L;
        Long userId = 1L;
        Message message = new Message();
        message.setMessageId(messageId);
        User sender = new User();
        sender.setUserId(userId);
        message.setSender(sender);
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message));

        messageService.deleteMessage(messageId, userId);

        verify(messageRepository).findById(messageId);
        verify(messageRepository).delete(message);
    }


    @Test
    public void testDeleteMessage_UserIsNotSender() {
        Long messageId = 1L;
        Long userId = 1L;
        Long senderId = 2L;
        Message message = new Message();
        message.setMessageId(messageId);
        User sender = new User();
        sender.setUserId(senderId);
        message.setSender(sender);
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message));

        assertThrows(MiscommunicationException.class, () -> messageService.deleteMessage(messageId, userId));
        verify(messageRepository).findById(messageId);
        verify(messageRepository, never()).delete(any());
    }

    @Test
    public void testGetById_MessageExists() {
        Long messageId = 1L;
        Message message = new Message();
        message.setMessageId(messageId);
        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message));

        Optional<Message> retrievedMessage = messageService.getById(messageId);

        assertTrue(retrievedMessage.isPresent());
        assertEquals(message, retrievedMessage.get());
        verify(messageRepository).findById(messageId);
    }

    @Test
    public void testGetById_MessageDoesNotExist() {
        Long messageId = 1L;
        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        // Act
        Optional<Message> retrievedMessage = messageService.getById(messageId);

        // Assert
         assertFalse(retrievedMessage.isPresent());
        verify(messageRepository).findById(messageId);
    }
}