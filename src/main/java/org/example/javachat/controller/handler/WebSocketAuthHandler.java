package org.example.javachat.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.javachat.controller.command.JavaChatCommand;
import org.example.javachat.controller.command.factory.CommandFactory;
import org.example.javachat.controller.command.request.FetchMessagesRequest;
import org.example.javachat.controller.command.request.JavaChatRequestVisitor;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.SendMessageRequest;
import org.example.javachat.controller.rest.filter.JwtTokenProvider;
import org.example.javachat.exception.MiscommunicationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketAuthHandler extends TextWebSocketHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final CommandFactory commandFactory;
    private final ObjectMapper objectMapper;

    public WebSocketAuthHandler(JwtTokenProvider jwtTokenProvider, CommandFactory commandFactory, ObjectMapper objectMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.commandFactory = commandFactory;
        this.objectMapper = objectMapper;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, ClassNotFoundException {

        var payload = message.getPayload();
        var request = objectMapper.readValue(payload, JavaChatWebSocketRequest.class);

        //TODO: encrypt connection, or man in the middle will be very happy
        var token = request.getToken();

        if (token == null || !jwtTokenProvider.validateToken(token)) {
            session.close();
            throw new MiscommunicationException("Access denied!");
        }

        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        if (authentication == null) {
            session.close();
            throw new MiscommunicationException("Access denied!");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.accept(new JavaChatRequestVisitor() {
            JavaChatCommand<? extends JavaChatWebSocketRequest> command;
            @Override
            public void visit(SendMessageRequest sendMessageRequest) {
                command = commandFactory.getCommand(sendMessageRequest.getRequestClass());
                command.execute(session, request, authentication);
            }

            @Override
            public void visit(FetchMessagesRequest fetchMessagesRequest) {
                command = commandFactory.getCommand(fetchMessagesRequest.getRequestClass());
                command.execute(session, request, authentication);
            }

        });

    }

}