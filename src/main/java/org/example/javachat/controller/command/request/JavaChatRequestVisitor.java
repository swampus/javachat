package org.example.javachat.controller.command.request;

public interface JavaChatRequestVisitor {
    void visit(SendMessageRequest request);
    void visit(FetchMessagesRequest request);
}
