package org.example.javachat.controller.command.factory;

import org.example.javachat.controller.command.FetchMessagesCommand;
import org.example.javachat.controller.command.JavaChatCommand;
import org.example.javachat.controller.command.SendMessageCommand;
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
    public CommandFactory(FetchMessagesCommand fetchMessagesCommand,
                          SendMessageCommand sendMessageCommand) {
        commands = new ConcurrentHashMap<>(new HashMap<>());
        commands.put(FetchMessagesRequest.class, fetchMessagesCommand);
        commands.put(SendMessageRequest.class, sendMessageCommand);
     }

    public JavaChatCommand<? extends JavaChatWebSocketRequest> getCommand(Class<? extends JavaChatWebSocketRequest>
                                                                                  requestClass) {
        return commands.get(requestClass);
    }
}