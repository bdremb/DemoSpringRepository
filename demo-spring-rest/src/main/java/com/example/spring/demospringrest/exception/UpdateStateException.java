package com.example.spring.demospringrest.exception;

public class UpdateStateException extends RuntimeException{
    public UpdateStateException(String message) {
        super(message);
    }
}
