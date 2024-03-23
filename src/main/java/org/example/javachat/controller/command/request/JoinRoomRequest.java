package org.example.javachat.controller.command.request;

public class JoinRoomRequest implements JavaChatWebSocketRequest {
    private Long userId;
    private Long roomId;

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

    @Override
    public Class<? extends JavaChatWebSocketRequest> getRequestClass() {
        return JoinRoomRequest.class;
    }
}
