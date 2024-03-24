package org.example.javachat.controller.rest.request;

public class JoinRoomRequest {

    private Long roomId;

    public JoinRoomRequest() {
    }

    public JoinRoomRequest(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
