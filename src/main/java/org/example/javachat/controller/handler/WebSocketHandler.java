package org.example.javachat.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.javachat.controller.command.factory.CommandFactory;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private CommandFactory commandFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        var request = objectMapper.readValue(payload, JavaChatWebSocketRequest.class);
        var requestClass = request.getClass();
        var command = commandFactory.getCommand(requestClass);

        command.execute(session, request);
    }

}