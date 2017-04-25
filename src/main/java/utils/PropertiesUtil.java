package utils;

import java.util.Properties;

public class PropertiesUtil {

    public static final String WS_DIR = "ws.dir";
    
    private static Properties properties;

    public static Properties getProperties() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(Properties.class.getResourceAsStream("/project.properties"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return properties;
    }
}
