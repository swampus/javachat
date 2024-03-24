package org.example.javachat.config;

import org.example.javachat.controller.handler.WebSocketAuthHandler;
import org.example.javachat.controller.handler.WebSocketAuthInterceptor;
import org.example.javachat.controller.rest.filter.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  //  private final JwtTokenProvider jwtTokenProvider;

    private final WebSocketAuthHandler webSocketAuthHandler;

    public WebSocketConfig(WebSocketAuthHandler webSocketAuthHandler) {
        this.webSocketAuthHandler = webSocketAuthHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketAuthHandler, "/chat")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }

}
