package com.example.demospringstarter.core.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
@Slf4j
public class TaskStopper {

    private final Map<String, ScheduledFuture<?>> tasks;

    public void stop(String key) {
        if(tasks.containsKey(key)) {
            var currentTask = tasks.get(key);
            currentTask.cancel(true);
            tasks.remove(key);

            log.debug("Task with key {} stopped", key);
        }
    }

    public void registryTask(String id, ScheduledFuture<?> future) {
        tasks.put(id, future);
    }

}
