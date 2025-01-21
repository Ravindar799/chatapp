package com.app.server.config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;

public class ConfigLoader {
    public static ServerConfig loadConfig() {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.yml")
        ) {
            if (inputStream == null) {
                throw new IllegalArgumentException("config.yml not found in resources folder");
            }
            return yaml.loadAs(inputStream, ServerConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration", e);
        }
    }
}

