package org.example.javachat.controller.command.request;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("SendMessageRequest")
public class SendMessageRequest extends JavaChatWebSocketRequest {
    private Long userId;
    private Long roomId;
    private String content;
    private String token;

    @Override
    public void accept(JavaChatRequestVisitor visitor) {
        visitor.visit(this);
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Class<? extends JavaChatWebSocketRequest> getRequestClass() {
        return SendMessageRequest.class;
    }
}
