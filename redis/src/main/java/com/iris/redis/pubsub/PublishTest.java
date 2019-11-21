package com.iris.redis.pubsub;

import redis.clients.jedis.Jedis;

/**
 * @Author: iris
 * @Date: 2019/9/27 10:55
 * @Description:
 */
public class PublishTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.publish("iris-123", "666");
        jedis.publish("iris-abc", "pengyuyan");
    }
}
