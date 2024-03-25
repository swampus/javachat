package org.example.javachat.service;

import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.model.ChatRoom;
import org.example.javachat.model.User;
import org.example.javachat.repository.ChatRoomRepository;
import org.example.javachat.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ChatRoomServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    @InjectMocks
    private ChatRoomService chatRoomService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testJoinRoom_ValidUserAndRoom() {
        Long userId = 1L;
        Long roomId = 1L;
        User user = new User();
        ChatRoom room = new ChatRoom();
        room.setUsers(new HashSet<>());
        room.setRoomId(roomId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.of(room));

        chatRoomService.joinRoom(userId, roomId);

        verify(chatRoomRepository).save(room);
        assert room.getUsers().contains(user);
    }

    @Test
    public void testJoinRoom_InvalidRoom() {
        Long userId = 1L;
        Long roomId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.empty());

        assertThrows(MiscommunicationException.class, () -> chatRoomService.joinRoom(userId, roomId));
        verify(chatRoomRepository, never()).save(any());
    }

    @Test
    public void testLeaveRoom_ValidUserAndRoom() {
        Long userId = 1L;
        Long roomId = 1L;
        User user = new User();
        ChatRoom room = new ChatRoom();
        room.setRoomId(roomId);
        room.setUsers(new HashSet<>());
        room.getUsers().add(user);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(chatRoomRepository.findById(roomId)).thenReturn(Optional.of(room));

        chatRoomService.leaveRoom(userId, roomId);

        verify(chatRoomRepository).save(room);
        assert !room.getUsers().contains(user);
    }

}