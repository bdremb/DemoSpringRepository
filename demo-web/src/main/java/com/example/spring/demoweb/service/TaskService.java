package com.example.spring.demoweb.service;

import com.example.spring.demoweb.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task getById(Long id);

    Task save(Task task);

    Task update(Task task);

    void deleteById(Long id);
}
