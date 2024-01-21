package com.example.demospringstarter.core;

public interface BackgroundTaskExecutor {

    void schedule(String taskId, Runnable task);
}
