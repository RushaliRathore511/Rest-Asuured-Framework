package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static{
        try {
            loadProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


private static void loadProperties() throws IOException {

    String env = System.getProperty("env", "qa");

    String fileName = "config/" + env + ".properties";

    InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(fileName);

    if (inputStream == null) {
        throw new RuntimeException("Failed to load Configuration file:" + fileName);
    }
    properties.load(inputStream);
}

    public static String get(String key){
        String value =   properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(
                    "Configuration key not found: " + key
            );
        }
        return value;
    }

    public static String getBaseUrl(){
        return get("base.url");
}

    public static String getAuthUrl(){
        return get("auth.url");
    }
}
