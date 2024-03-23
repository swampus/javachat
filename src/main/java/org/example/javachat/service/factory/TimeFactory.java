package org.example.javachat.service.factory;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TimeFactory {
    public Timestamp currentAppTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }
}
