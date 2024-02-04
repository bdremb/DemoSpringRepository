package com.example.spring.demojooq.exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
