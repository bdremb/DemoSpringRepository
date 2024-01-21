package com.example.demosocketserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoSocketserverApplication {

    @Autowired
    private Server server;

    public static void main(String[] args) {
        SpringApplication.run(DemoSocketserverApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startServer() {
        server.start();
    }

}
