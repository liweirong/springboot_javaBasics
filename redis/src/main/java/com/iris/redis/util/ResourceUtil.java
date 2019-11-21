package com.iris.redis.util;

import java.util.ResourceBundle;

/**
 * @Author: iris
 * @Date: 2018/11/16 15:34
 * @Description:
 * 配置文件读取工具类
 */
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("redis");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

}
