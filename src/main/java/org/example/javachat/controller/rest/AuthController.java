package org.example.javachat.controller.rest;

import org.example.javachat.controller.rest.filter.JwtTokenProvider;
import org.example.javachat.controller.rest.request.AuthRequest;
import org.example.javachat.controller.rest.response.JwtAuthResponse;
import org.example.javachat.model.User;
import org.example.javachat.service.JavaChatPasswordEncoder;
import org.example.javachat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rest")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaChatPasswordEncoder passwordEncoder;

    @PostMapping(value = "/auth", consumes = "Application/json")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        try {
            var user = userService.getUserByUsername(authRequest.getUsername());
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid username or password");
            }
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var token = jwtTokenProvider.generateToken(String.valueOf(user.getUserId()), user.getUsername());
            return ResponseEntity.ok(new JwtAuthResponse(token));
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
    private static JwtAuthResponse createResponse(String token) {
        return new JwtAuthResponse(token);
    }
}
