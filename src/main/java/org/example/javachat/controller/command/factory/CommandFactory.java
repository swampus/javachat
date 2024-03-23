package org.example.javachat.controller.command.factory;

import org.example.javachat.controller.command.*;
import org.example.javachat.controller.command.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandFactory {

    private final Map<Class<? extends JavaChatWebSocketRequest>,
            JavaChatCommand<? extends JavaChatWebSocketRequest>> commands;

    @Autowired
    public CommandFactory(JoinRoomCommand joinCommand,
                          LeaveRoomCommand leaveCommand,
                          FetchMessagesCommand fetchMessagesCommand,
                          SendMessageCommand sendMessageCommand,
                          DeleteMessageCommand deleteMessageCommand,
                          AuthCommand authCommand) {
        commands = new ConcurrentHashMap<>(new HashMap<>());
        commands.put(JoinRoomRequest.class, joinCommand);
        commands.put(LeaveRoomRequest.class, leaveCommand);
        commands.put(FetchMessagesRequest.class, fetchMessagesCommand);
        commands.put(DeleteOwnMessageRequest.class, deleteMessageCommand);
        commands.put(SendMessageRequest.class, sendMessageCommand);
        commands.put(AuthRequest.class, authCommand);
    }

    public JavaChatCommand<? extends JavaChatWebSocketRequest> getCommand(Class<? extends JavaChatWebSocketRequest>
                                                                                  requestClass) {
        return commands.get(requestClass);
    }
}