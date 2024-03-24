package org.example.javachat.controller.rest.request;

public class DeleteOwnMessageRequest {

    private Long messageId;

    public DeleteOwnMessageRequest() {
    }

    public DeleteOwnMessageRequest(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
