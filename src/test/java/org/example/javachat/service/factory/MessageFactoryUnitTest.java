package org.example.javachat.service.factory;

import org.example.javachat.model.ChatRoom;
import org.example.javachat.model.Message;
import org.example.javachat.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageFactoryUnitTest {

    @Mock
    private TimeFactory timeFactory;

    @InjectMocks
    private MessageFactory messageFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMessage() {
        User user = new User();
        ChatRoom room = new ChatRoom();
        String content = "Test message";
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        when(timeFactory.currentAppTimestamp()).thenReturn(currentTimestamp);

        Message message = messageFactory.createMessage(user, room, content);

        assertEquals(content, message.getMessageText());
        assertEquals(user, message.getSender());
        assertEquals(room, message.getRoom());
        assertEquals(currentTimestamp, message.getTimestamp());
        verify(timeFactory, times(1)).currentAppTimestamp();
    }
}