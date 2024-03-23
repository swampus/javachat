package org.example.javachat.service.factory;

import org.example.javachat.model.ChatRoom;
import org.example.javachat.model.Message;
import org.example.javachat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {

    @Autowired
    private TimeFactory timeFactory;

    public Message createMessage(User user, ChatRoom room, String content) {
        var message = new Message();
        message.setMessageText(content);
        message.setSender(user);
        message.setRoom(room);
        message.setTimestamp(timeFactory.currentAppTimestamp());
        return message;
    }
}
