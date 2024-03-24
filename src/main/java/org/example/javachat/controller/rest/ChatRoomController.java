package org.example.javachat.controller.rest;

import org.example.javachat.controller.rest.request.JoinRoomRequest;
import org.example.javachat.controller.rest.request.LeaveRoomRequest;
import org.example.javachat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rest/chat")
public class ChatRoomController extends AbstractRestController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PutMapping(value = "/room/left", consumes = "Application/json")
    @Secured("ROLE_USER")
    public void leaveRoom(@RequestBody LeaveRoomRequest leaveRoomRequest, Authentication authentication) {
        var currentUserId = getCurrentUserId(authentication);
        chatRoomService.leaveRoom(currentUserId, leaveRoomRequest.getRoomId());
    }

    @PutMapping(value = "/room/join", consumes = "Application/json")
    @Secured("ROLE_USER")
    public void joinRoom(@RequestBody JoinRoomRequest joinRoomRequest, Authentication authentication) {
        var currentUserId = getCurrentUserId(authentication);
        chatRoomService.joinRoom(currentUserId, joinRoomRequest.getRoomId());
    }

}
