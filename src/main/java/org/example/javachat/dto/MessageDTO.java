package org.example.javachat.dto;

import java.sql.Timestamp;

public class MessageDTO {

    private Long messageId;
    private UserDTO sender;
    private ChatRoomDTO room;
    private String messageText;
    private Timestamp timestamp;
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public ChatRoomDTO getRoom() {
        return room;
    }

    public void setRoom(ChatRoomDTO room) {
        this.room = room;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}