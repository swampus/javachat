package org.example.javachat.controller.command.request;

public class FetchMessagesRequest implements JavaChatWebSocketRequest {

    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public Class<? extends JavaChatWebSocketRequest> getRequestClass() {
        return FetchMessagesRequest.class;
    }
}
