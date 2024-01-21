package com.example.demosocketclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

@Component
public class Client {

    @Value("${app.server.host}")
    private String host;

    @Value("${app.server.port}")
    private int port;

    public void sentToServer(String data) {
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server: " + socket);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println(data);
            System.out.println("Sent request to server: " + data);

            String response = reader.readLine();
            System.out.println("Received response from server: " + response);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void send() {
        sentToServer("Send data from client. Data is: " + UUID.randomUUID());
    }

}
