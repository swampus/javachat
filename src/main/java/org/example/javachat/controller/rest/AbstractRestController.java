package org.example.javachat.controller.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class AbstractRestController {
    protected Long getCurrentUserId(Authentication authentication) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        return Long.parseLong(userDetails.getUsername()); // contains Long:userId -> UserService
    }
}
