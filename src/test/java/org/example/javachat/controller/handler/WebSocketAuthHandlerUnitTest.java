package org.example.javachat.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.javachat.controller.command.JavaChatCommand;
import org.example.javachat.controller.command.factory.CommandFactory;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.SendMessageRequest;
import org.example.javachat.controller.rest.filter.JwtTokenProvider;
import org.example.javachat.exception.MiscommunicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WebSocketAuthHandlerUnitTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private CommandFactory commandFactory;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private WebSocketAuthHandler webSocketAuthHandler;

    @Mock
    private WebSocketSession session;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleTextMessage_ValidToken_SendMessage() throws Exception {
        String validToken = "validToken";
        String payload = "{\"token\":\"validToken\"}";
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setToken(validToken);
        JavaChatCommand command = mock(JavaChatCommand.class);

        when(objectMapper.readValue(payload, JavaChatWebSocketRequest.class)).thenReturn(sendMessageRequest);
        when(jwtTokenProvider.validateToken(validToken)).thenReturn(true);
        when(jwtTokenProvider.getAuthentication(validToken)).thenReturn(authentication);
        when(commandFactory.getCommand(any())).thenReturn(command);

        webSocketAuthHandler.handleTextMessage(session, new TextMessage(payload));

        verify(command).execute(session, sendMessageRequest, authentication);
    }

    @Test
    public void testHandleTextMessage_InvalidToken() throws Exception {
        String invalidToken = "invalidToken";
        String payload = "{\"token\":\"invalidToken\"}";

        when(objectMapper.readValue(payload, JavaChatWebSocketRequest.class)).thenReturn(new SendMessageRequest());
        when(jwtTokenProvider.validateToken(invalidToken)).thenReturn(false);

        MiscommunicationException exception = assertThrows(MiscommunicationException.class,
                () -> webSocketAuthHandler.handleTextMessage(session, new TextMessage(payload)));
        assertEquals("Access denied!", exception.getMessage());
        verify(session).close();
    }

}