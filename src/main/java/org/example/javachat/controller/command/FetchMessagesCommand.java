package org.example.javachat.controller.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.javachat.controller.command.request.FetchMessagesRequest;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.example.javachat.dto.ChatRoomDTO;
import org.example.javachat.dto.MessageDTO;
import org.example.javachat.dto.UserDTO;
import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.model.ChatRoom;
import org.example.javachat.model.Message;
import org.example.javachat.model.User;
import org.example.javachat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FetchMessagesCommand implements JavaChatCommand<FetchMessagesRequest> {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public void execute(WebSocketSession session, JavaChatWebSocketRequest request, Authentication authentication) {
        var targetRequest = castToRequest(request);
        Long currentUserId = getCurrentUserId(authentication);

        List<Message> messages = messageService.fetchMessages(currentUserId, targetRequest.getRoomId());
        List<MessageDTO> messageDTOs = convertMessagesToDTOs(messages);


        for (MessageDTO messageDTO : messageDTOs) {
            try {
                String json = objectMapper.writeValueAsString(messageDTO);
                session.sendMessage(new TextMessage(json));
            } catch (IOException e) {
                throw new MiscommunicationException(e.getMessage(), e);
            }
        }
    }

    @Override
    public FetchMessagesRequest castToRequest(JavaChatWebSocketRequest javaChatWebSocketRequest) {
        if (!(javaChatWebSocketRequest instanceof FetchMessagesRequest)) {
            throw new MiscommunicationException("Expected FetchMessagesRequest");
        }
        return (FetchMessagesRequest) javaChatWebSocketRequest;
    }

    private Long getCurrentUserId(Authentication authentication) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        return Long.parseLong(userDetails.getUsername());
    }

    private List<MessageDTO> convertMessagesToDTOs(List<Message> messages) {
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message message : messages) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessageId(message.getMessageId());
            messageDTO.setSender(convertUserToDTO(message.getSender()));
            messageDTO.setRoom(convertRoomToDTO(message.getRoom()));
            messageDTO.setMessageText(message.getMessageText());
            messageDTO.setTimestamp(message.getTimestamp());
            messageDTOs.add(messageDTO);
        }
        return messageDTOs;
    }

    private UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        return userDTO;
    }

    private ChatRoomDTO convertRoomToDTO(ChatRoom room) {
        ChatRoomDTO roomDTO = new ChatRoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        return roomDTO;
    }
}
