package com.app.server;

import com.app.server.config.ConfigLoader;
import com.app.server.config.ServerConfig;
import com.app.server.implementations.ChatServerImpl;
import com.app.server.interfaces.ChatServer;

public class ServerAppFactory {

    public static ChatServer createServer() {
        // Load configuration from the YAML file
        ServerConfig config = ConfigLoader.loadConfig();
        int port = config.getServer().getPort();

        // Pass the port to the server
        return new ChatServerImpl(port);
    }
}
