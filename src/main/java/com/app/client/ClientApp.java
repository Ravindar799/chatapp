package com.app.client;

import com.app.client.implementations.ChatClientImpl;
import com.app.client.interfaces.ChatClient;

import java.util.Scanner;

public class ClientApp {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        ChatClient client = new ChatClientImpl(SERVER_ADDRESS, SERVER_PORT);
        client.connect();
        client.receiveMessages();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter messages to send to the chat server:");

        while (true) {
            String message = scanner.nextLine();
            if ("exit".equalsIgnoreCase(message)) {
                break;
            }
            client.sendMessage(message);
        }

        System.out.println("Client disconnected.");
        scanner.close();
    }
}

