package com.example.finalproject.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 9090;

        try (Socket socket = new Socket(hostname, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to Socket Server on port " + port);

            // Send Command
            String command = "STATS";
            System.out.println("Sending command: " + command);
            out.println(command);

            // Read Response
            String response = in.readLine();
            System.out.println("Server Response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
