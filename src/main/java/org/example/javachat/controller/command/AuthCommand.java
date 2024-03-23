package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.AuthRequest;
import org.example.javachat.controller.command.request.FetchMessagesRequest;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.JoinRoomRequest;
import org.example.javachat.exception.MiscommunicationException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class AuthCommand implements JavaChatCommand<AuthRequest> {
    @Override
    public void execute(WebSocketSession session, JavaChatWebSocketRequest request) {

    }

    @Override
    public AuthRequest castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest) {
        if (!(javaChatWebSocketRequest instanceof AuthRequest)) {
            throw new MiscommunicationException("Expected AuthRequest");
        }
        return (AuthRequest) javaChatWebSocketRequest;
    }

}
