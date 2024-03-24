package org.example.javachat.controller.rest.response;

public class JwtAuthResponse {

    private String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
