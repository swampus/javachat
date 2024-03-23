package org.example.javachat.controller.command.request;

public class AuthRequest implements JavaChatWebSocketRequest {
    @Override
    public Class<? extends JavaChatWebSocketRequest> getRequestClass() {
        return AuthRequest.class;
    }
}
