package org.example.javachat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.javachat.controller.command.request.JavaChatWebSocketRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        //objectMapper.addMixIn(JavaChatWebSocketRequest.class, JavaChatWebSocketRequestMixin.class);
        return objectMapper;
    }
}
