package com.iris.redis.datatype;


import com.iris.redis.util.JedisUtil;

import java.util.Set;

/**
 * @Author: iris
 * @Date: 2019/9/19 23:35
 * @Description:
 */
public class ZSetTest {
    public static void main(String[] args) {
        JedisUtil.getJedisUtil().zadd("myzset", 20, "java");
        JedisUtil.getJedisUtil().zadd("myzset", 30, "python");
        JedisUtil.getJedisUtil().zadd("myzset", 90, "ruby");
        JedisUtil.getJedisUtil().zadd("myzset", 40, "erlang");
        JedisUtil.getJedisUtil().zadd("myzset", 70, "cpp");
        JedisUtil.getJedisUtil().zadd("myzset", 50, "android");
        Set<String> set = JedisUtil.getJedisUtil().zrangeByScore("myzset", 100, 10);
        System.out.println(set);// 从大到小
    }
}
