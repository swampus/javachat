package org.example.javachat.controller.rest;

import org.example.javachat.controller.rest.request.DeleteOwnMessageRequest;
import org.example.javachat.controller.rest.request.LeaveRoomRequest;
import org.example.javachat.exception.MiscommunicationException;
import org.example.javachat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rest/chat")
public class MessageController extends AbstractRestController {

    @Autowired
    private MessageService messageService;
    @PutMapping(value = "/message/delete", consumes = "Application/json")
    @Secured("ROLE_USER")
    public void deleteMessage(@RequestBody DeleteOwnMessageRequest request, Authentication authentication) {
        var userId = getCurrentUserId(authentication);
        messageService.deleteMessage(request.getMessageId(), userId);
    }

}
