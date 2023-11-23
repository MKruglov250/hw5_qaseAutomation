package org.example.utilities;

import io.qameta.allure.Step;

import java.io.*;
import java.util.Properties;

public abstract class PropertyReader {
    private static Properties properties;

    private static void readProperties() throws FileNotFoundException {
        properties = new Properties();
        File initialFile = new File("src/resources/config.properties");
        InputStream inputStream = new FileInputStream(initialFile);
        try {
            properties.load(inputStream);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) throws FileNotFoundException {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty(key);
    }

    @Step("Get browser to perform test")
    public static String getBrowserProperty( ) throws FileNotFoundException {
        return getProperty("Browser");
    }

    @Step("Get timeout for locating elements")
    public static int getTimeoutProperty( ) throws FileNotFoundException {
        return Integer.parseInt(getProperty("Timeout"));
    }

}
