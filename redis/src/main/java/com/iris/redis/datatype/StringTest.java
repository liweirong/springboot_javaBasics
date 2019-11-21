package com.iris.redis.datatype;

import com.iris.redis.util.JedisUtil;

/**
 * @Author: iris
 * @Date: 2019/9/17 19:44
 * @Description:
 */
public class StringTest {
    public static void main(String[] args) {
        JedisUtil.getJedisUtil().set("iris", "2673");
        // JedisUtil.getJedisUtil().incr("iris");

        String qs = JedisUtil.getJedisUtil().get("iris");
        System.out.println(qs);


    }
}
