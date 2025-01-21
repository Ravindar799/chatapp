package com.app.server.implementations;

import com.app.server.interfaces.ChatServer;
import com.app.server.interfaces.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Accept messages from a client and broadcast these messages to other clients
public class ClientHandlerImpl implements ClientHandler {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ChatServer chatServer;

    public ClientHandlerImpl(Socket socket, ChatServer chatServer) {
        this.socket = socket;
        this.chatServer = chatServer;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            sendMessage("Welcome to the chat!");

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);
                chatServer.broadcast(message, this);
            }
        } catch (IOException e) {
            System.err.println("Client disconnected: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    @Override
    public void sendMessage(String message) {
        out.println(message);
    }

    private void cleanup() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing socket: " + e.getMessage());
        }
        chatServer.removeClient(this);
    }
}