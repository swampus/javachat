package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.FetchMessagesRequest;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.exception.MiscommunicationException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class FetchMessagesCommand implements JavaChatCommand<FetchMessagesRequest> {

    @Override
    public void execute(WebSocketSession session, JavaChatWebSocketRequest request) {

    }

    @Override
    public FetchMessagesRequest castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest) {
        if (!(javaChatWebSocketRequest instanceof FetchMessagesRequest)) {
            throw new MiscommunicationException("Expected FetchMessagesRequest");
        }
        return (FetchMessagesRequest) javaChatWebSocketRequest;
    }
}
