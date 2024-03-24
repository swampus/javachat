package org.example.javachat.controller.rest.request;

import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;

public class AuthRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
