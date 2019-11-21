package com.iris.redis.datatype;

import com.iris.redis.util.JedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: iris
 * @Date: 2019/9/18 16:31
 * @Description:
 */
public class HashTest {
    public static void main(String[] args) {
        String h1 = JedisUtil.getJedisUtil().hget("h1", "a");
        System.out.println(h1);

        List<String> list = new ArrayList<String>();
        list = JedisUtil.getJedisUtil().hmget("h1","a","b","c","d","e");
        System.out.println(list);
    }
}
