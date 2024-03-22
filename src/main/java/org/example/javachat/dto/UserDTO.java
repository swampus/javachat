package org.example.javachat.dto;

import java.util.Set;

public class UserDTO {
    private Long userId;
    private String username;
    private Set<Long> chatRoomIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Long> getChatRoomIds() {
        return chatRoomIds;
    }

    public void setChatRoomIds(Set<Long> chatRoomIds) {
        this.chatRoomIds = chatRoomIds;
    }
}
