package com.app.server.interfaces;

public interface ClientHandler extends Runnable {
    void sendMessage(String message);
}
