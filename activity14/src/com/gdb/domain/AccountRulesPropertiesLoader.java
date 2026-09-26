package com.gdb.domain;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class AccountRulesPropertiesLoader {

    private final Properties properties = new Properties();

    public AccountRulesPropertiesLoader(String filePath) {
        loadProperties(filePath);
    }

    private void loadProperties(String filePath) {
        try {
            InputStream inputStream;

            try {
                inputStream = getClass().getClassLoader()
                        .getResourceAsStream(filePath);
            } catch (Exception e) {
                inputStream = null;
            }

            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            } else {
                FileInputStream fileInputStream =
                        new FileInputStream(filePath);

                properties.load(fileInputStream);
                fileInputStream.close();
            }

            System.out.println("[Config] Loaded rules from " + filePath);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to load properties file: " + filePath, e);
        }
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public double getDouble(String key, double defaultValue) {
        String value = properties.getProperty(key);

        if (value == null) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}