package com.iris.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisStringReactiveCommands;

/**
 * @Author: iris
 * @Date: 2019/9/29 11:53
 * @Description:
 */
public class ReactiveAPI {
    public static void main(String[] args) {
        // 客户端
        RedisClient client = RedisClient.create("redis://127.0.0.1:6379");

        // 建立连接
        StatefulRedisConnection<String, String> connection = client.connect();

        // reactive
        RedisStringReactiveCommands<String, String> reactive = connection.reactive();

        reactive.get("iris:reactive").subscribe(System.out::println);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        connection.close();
        client.shutdown();
    }
}
