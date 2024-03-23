package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.LeaveRoomRequest;
import org.example.javachat.controller.command.request.SendMessageRequest;
import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
public class LeaveRoomCommand implements JavaChatCommand<LeaveRoomRequest> {

    @Autowired
    private ChatRoomService chatRoomService;
    @Override
    public void execute(WebSocketSession session, JavaChatWebSocketRequest request) {
        var targetRequest = castToRequest(request);
        chatRoomService.leaveRoom(targetRequest.getUserId(), targetRequest.getRoomId());
    }

    @Override
    public LeaveRoomRequest castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest) {
        if (!(javaChatWebSocketRequest instanceof LeaveRoomRequest)) {
            throw new MiscommunicationException("Expected LeaveRoomRequest");
        }
        return (LeaveRoomRequest) javaChatWebSocketRequest;
    }
}
