package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.JoinRoomRequest;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public interface JavaChatCommand<T extends JavaChatWebSocketRequest> {
    void execute(WebSocketSession session, JavaChatWebSocketRequest request);
    T castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest);
}
