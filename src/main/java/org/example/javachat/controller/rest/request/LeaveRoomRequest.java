package org.example.javachat.controller.rest.request;

public class LeaveRoomRequest {

    private Long roomId;

    public LeaveRoomRequest(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

}
