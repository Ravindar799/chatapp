package com.app.server.interfaces;

public interface ChatServer {
    void start();
    void broadcast(String message, ClientHandler sender);
    void removeClient(ClientHandler clientHandler);
}
