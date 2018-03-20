package com.kanmenzhu;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Config {

    private static Properties prop;

    static {
        try {
            prop = new Properties();
            InputStream intwo = Object.class.getResourceAsStream("/parm.properties");
            InputStreamReader readtwo = new InputStreamReader(intwo, "UTF-8");
            prop.load(readtwo); /// 加载属性列表
            intwo.close();
            readtwo.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readConfig(File file) {
        try {
            // 读取属性文件
            InputStream in = new FileInputStream(file);
            InputStreamReader read = new InputStreamReader(in, "UTF-8");
            prop.load(read); /// 加载属性列表
            in.close();
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return value
     */
    public static String get(String key) {
        return prop.getProperty(key);
    }

    /**
     * 获取所有属性
     *
     * @return map
     */
    public static Map<String, String> getAll() {
        Map<String, String> all = new HashMap<String, String>();
        Iterator<String> it = prop.stringPropertyNames().iterator();
        while (it.hasNext()) {
            String key = it.next();
            all.put(key, prop.getProperty(key));
        }
        return all;
    }

    public static void main(String[] args) {
        System.out.println(Config.get("LoopController.continue_forever"));
    }
}
