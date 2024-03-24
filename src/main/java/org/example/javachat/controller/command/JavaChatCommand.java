package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.WebSocketSession;

public interface JavaChatCommand<T extends JavaChatWebSocketRequest> {
    void execute(WebSocketSession session, JavaChatWebSocketRequest request, Authentication authentication);
    T castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest);
}
