package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.SendMessageRequest;
import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class SendMessageCommand implements JavaChatCommand<SendMessageRequest> {

    @Autowired
    private MessageService messageService;

    @Override
    public void execute(WebSocketSession session, JavaChatWebSocketRequest request, Authentication authentication) {
        var targetRequest = castToRequest(request);
        Long currentUserId = getCurrentUserId(authentication);
        if (currentUserId.equals(targetRequest.getUserId())) {
            messageService.sendMessage(targetRequest.getUserId(), targetRequest.getRoomId(), targetRequest.getContent());
        } else {
            throw new MiscommunicationException("You are not authorized to send this message");
        }
    }

    @Override
    public SendMessageRequest castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest) {
        if (!(javaChatWebSocketRequest instanceof SendMessageRequest)) {
            throw new MiscommunicationException("Expected SendMessageRequest");
        }
        return (SendMessageRequest) javaChatWebSocketRequest;
    }

    private Long getCurrentUserId(Authentication authentication) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        return Long.parseLong(userDetails.getUsername());
    }
}
