package com.app.client.interfaces;

public interface ChatClient {
    void connect();
    void sendMessage(String message);
    void receiveMessages();
}