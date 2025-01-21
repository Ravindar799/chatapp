package com.app.server;

import com.app.server.implementations.ChatServerImpl;
import com.app.server.interfaces.ChatServer;

public class ServerApp {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServerImpl(PORT);
        chatServer.start();
    }
}
