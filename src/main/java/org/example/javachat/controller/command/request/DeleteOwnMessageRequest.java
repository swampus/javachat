package org.example.javachat.controller.command.request;

public class DeleteOwnMessageRequest implements JavaChatWebSocketRequest {
    @Override
    public Class<? extends JavaChatWebSocketRequest> getRequestClass() {
        return DeleteOwnMessageRequest.class;
    }
}
