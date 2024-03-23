package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.DeleteOwnMessageRequest;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.JoinRoomRequest;
import org.example.javachat.exception.MiscommunicationException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class DeleteMessageCommand implements JavaChatCommand<DeleteOwnMessageRequest> {
    @Override
    public void execute(WebSocketSession session, JavaChatWebSocketRequest request) {

    }

    @Override
    public DeleteOwnMessageRequest castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest) {
        if (!(javaChatWebSocketRequest instanceof DeleteOwnMessageRequest)) {
            throw new MiscommunicationException("Expected DeleteOwnMessageRequest");
        }
        return (DeleteOwnMessageRequest) javaChatWebSocketRequest;
    }
}
