package com.example.spring.demoweb.model;

import lombok.Data;

@Data
public class Task {

    private Long id;
    private String title;
    private String description;
    private int priority;
}