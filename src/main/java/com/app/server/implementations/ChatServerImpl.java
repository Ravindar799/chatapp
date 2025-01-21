package com.app.server.implementations;

import com.app.server.interfaces.ChatServer;
import com.app.server.interfaces.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl implements ChatServer {
    private int port;
    private List<ClientHandler> clients;

    public ChatServerImpl(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
    }

    @Override
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandlerImpl(clientSocket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    @Override
    public synchronized void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    @Override
    public synchronized void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}
