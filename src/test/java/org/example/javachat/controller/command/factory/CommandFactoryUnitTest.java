package org.example.javachat.controller.command.factory;

import org.example.javachat.controller.command.FetchMessagesCommand;
import org.example.javachat.controller.command.SendMessageCommand;
import org.junit.jupiter.api.Test;

import org.example.javachat.controller.command.JavaChatCommand;
import org.example.javachat.controller.command.request.FetchMessagesRequest;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.SendMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandFactoryUnitTest {

    @Mock
    private FetchMessagesCommand fetchMessagesCommand;

    @Mock
    private SendMessageCommand sendMessageCommand;

    @InjectMocks
    private CommandFactory commandFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCommand_FetchMessagesRequest() {
        var command = commandFactory.getCommand(FetchMessagesRequest.class);
        assertEquals(fetchMessagesCommand, command);
    }

    @Test
    public void testGetCommand_SendMessageRequest() {
        var command = commandFactory.getCommand(SendMessageRequest.class);
        assertEquals(sendMessageCommand, command);
    }
}