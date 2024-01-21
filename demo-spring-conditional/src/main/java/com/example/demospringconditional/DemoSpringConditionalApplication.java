package com.example.demospringconditional;

import com.example.demospringstarter.core.BackgroundTaskExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.util.UUID;

@SpringBootApplication
@ComponentScan("com.example")
public class DemoSpringConditionalApplication {

    @Autowired
    private BackgroundTaskExecutor backgroundTaskExecutor;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringConditionalApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onEvent() {
        Thread thread = new Thread(() -> {
            while (true) {
                String id = UUID.randomUUID().toString();
                backgroundTaskExecutor.schedule(id, ()-> System.out.println("Task for " + id));
            }
        });
        thread.start();
    }

}
