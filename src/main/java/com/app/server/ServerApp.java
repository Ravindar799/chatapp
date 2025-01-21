package com.app.server;

import com.app.server.interfaces.ChatServer;

public class ServerApp {
    public static void main(String[] args) {
        ChatServer chatServer = ServerAppFactory.createServer();
        chatServer.start();
    }
}
