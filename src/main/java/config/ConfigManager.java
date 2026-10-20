package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {
    private static final Properties PROPERTIES = loadProperties();

    private ConfigManager() {
    }

    private static Properties loadProperties() {
        String env = System.getProperty("env", "qa");
        String fileName = "config/" + env + ".properties";
        Properties properties = new Properties();

        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException("Configuration file not found: " + fileName);
            }
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load configuration file: " + fileName, e);
        }
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Configuration key is missing: " + key);
        }
        return value.trim();
    }

    public static String getBaseUrl() {
        String override = System.getProperty("base.url");
        if (override == null || override.isBlank()) {
            override = System.getenv("API_BASE_URL");
        }
        return override == null || override.isBlank() ? get("base.url") : override.trim();
    }

    public static String getAuthUrl() {
        String override = System.getProperty("auth.url");
        if (override == null || override.isBlank()) {
            override = System.getenv("AUTH_URL");
        }
        if (override != null && !override.isBlank()) {
            return override.trim();
        }

        String configuredUrl = PROPERTIES.getProperty("auth.url");
        if (configuredUrl != null && !configuredUrl.isBlank()) {
            return configuredUrl.trim();
        }

        throw new IllegalStateException(
                "Auth endpoint is not configured. Set AUTH_URL or add auth.url to the selected environment properties.");
    }
}
