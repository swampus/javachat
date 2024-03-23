package org.example.javachat.controller.command;

import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.controller.command.request.JoinRoomRequest;
import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class JoinRoomCommand implements JavaChatCommand<JoinRoomRequest> {

    @Autowired
    private ChatRoomService chatRoomService;

    @Override
    public void execute(WebSocketSession session, JavaChatWebSocketRequest request) {
        var targetRequest = castToRequest(request);
        chatRoomService.joinRoom(targetRequest.getUserId(), targetRequest.getRoomId());
    }

    @Override
    public JoinRoomRequest castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest) {
        if (!(javaChatWebSocketRequest instanceof JoinRoomRequest)) {
            throw new MiscommunicationException("Expected JoinRoomRequest");
        }
        return (JoinRoomRequest) javaChatWebSocketRequest;
    }


}
