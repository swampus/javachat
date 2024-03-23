package org.example.javachat.controller.command.request;

public class SendMessageRequest implements JavaChatWebSocketRequest {
    private Long userId;
    private Long roomId;
    private String content;

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
