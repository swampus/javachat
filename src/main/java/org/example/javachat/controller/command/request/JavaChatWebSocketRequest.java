package org.example.javachat.controller.command.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SendMessageRequest.class, name = "SendMessageRequest"),
        @JsonSubTypes.Type(value = FetchMessagesRequest.class, name = "FetchMessagesRequest"),
})
public abstract class JavaChatWebSocketRequest {
    abstract Class<? extends JavaChatWebSocketRequest> getRequestClass();
    public abstract void accept(JavaChatRequestVisitor visitor);
    public abstract String getToken();
}
