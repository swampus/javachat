package org.example.javachat.exception;

public class MiscommunicationException extends  AbstractJavaChatException{

    public MiscommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MiscommunicationException(String message) {
        super(message);
    }

}
