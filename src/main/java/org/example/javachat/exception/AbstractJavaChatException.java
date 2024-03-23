package org.example.javachat.exception;

public abstract class AbstractJavaChatException extends RuntimeException {
    public AbstractJavaChatException(String message) {
        super(message);
    }

    public AbstractJavaChatException(String message, Throwable cause) {
        super(message, cause);
    }
}
