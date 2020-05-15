package com.cxyxh.c02_frame;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyMgr {
    static Properties properties = new Properties();
    static {
        try {
            //TODO 使用PropertyMgr.class.getClassLoader().getResourceAsStream("/config/config.properties")会获取为null
            PropertyMgr.class.getClassLoader().getResourceAsStream("/config/config.properties");
            InputStream resourceAsStream = PropertyMgr.class.getResourceAsStream("/config/config.properties");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        return properties.get(key);
    }

    public static Integer getInteger(String key){
        String value = properties.getProperty(key);
        return Integer.valueOf(value);
    }

    public static void main(String[] args) {
        Object o = properties.get("initial.enemy.count");
        System.out.println(o);
    }

}
