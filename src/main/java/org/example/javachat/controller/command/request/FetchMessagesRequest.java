package org.example.javachat.controller.command.request;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("FetchMessagesRequest")
public class FetchMessagesRequest extends JavaChatWebSocketRequest {

    private Long roomId;
    private String token;
    @Override
    public void accept(JavaChatRequestVisitor visitor) {
        visitor.visit(this);
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Class<? extends JavaChatWebSocketRequest> getRequestClass() {
        return FetchMessagesRequest.class;
    }


}
