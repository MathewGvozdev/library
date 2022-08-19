package com.mathewgv.library.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();
    private static final String APPLICATION_PROPERTIES = "application.properties";

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class
                .getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
