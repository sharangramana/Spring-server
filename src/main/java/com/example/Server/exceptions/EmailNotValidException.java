package com.example.Server.exceptions;

public class EmailNotValidException extends RuntimeException{
    public EmailNotValidException(String message) {
        super(message);
    }
}
