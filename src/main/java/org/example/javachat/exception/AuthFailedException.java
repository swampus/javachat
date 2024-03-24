package org.example.javachat.exception;

public class AuthFailedException extends AbstractJavaChatException {
    public AuthFailedException(String message) {
        super(message);
    }
}
