package org.example.javachat.controller.command.request;

public interface JavaChatWebSocketRequest {
    Class<? extends JavaChatWebSocketRequest> getRequestClass();
}
