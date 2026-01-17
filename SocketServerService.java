package com.example.finalproject.service;

import com.example.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class SocketServerService {

    @Autowired
    private UserRepository userRepository;

    private ServerSocket serverSocket;
    private boolean running = false;

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(9090);
                running = true;
                System.out.println("Socket Server started on port 9090");

                while (running) {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleClient(Socket clientSocket) {
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if ("STATS".equalsIgnoreCase(inputLine)) {
                        long count = userRepository.count();
                        out.println("User Count: " + count);
                    } else {
                        out.println("Unknown Command. Try STATS");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @PreDestroy
    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
