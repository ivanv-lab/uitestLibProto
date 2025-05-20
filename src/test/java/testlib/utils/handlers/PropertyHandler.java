package testlib.utils.handlers;

import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

    private static Properties properties=new Properties();

    static {
        try(InputStream inputStream= ClassLoader.getSystemResourceAsStream("selenide.properties")){

            if(inputStream==null){
                System.out.println("Не удалось найти файл \"selenide.properties\"");
                throw new IllegalStateException("Не удалось загрузить свойства");
            }

            properties.load(inputStream);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static String getProperty(String property){
        return properties.getProperty(property);
    }
}
