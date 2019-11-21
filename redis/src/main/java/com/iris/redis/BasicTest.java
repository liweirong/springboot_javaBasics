package com.iris.redis;

import redis.clients.jedis.Jedis;

/**
 * @Author: iris
 * @Date: 2019/9/26 17:58
 * @Description:
 */
public class BasicTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("iris", "2673");
        System.out.println(jedis.get("iris"));
        jedis.close();
    }
}
