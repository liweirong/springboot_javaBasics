package com.iris.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author iris
 */
public class LettuceASyncTest {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://192.168.44.181:6379");
        // 线程安全的长连接，连接丢失时会自动重连
        StatefulRedisConnection<String, String> connection = client.connect();
        // 获取异步执行命令api
        RedisAsyncCommands<String, String> commands = connection.async();
        // 获取RedisFuture<T>
        commands.set("gupao:async","lettuce-async-666");
        RedisFuture<String> future = commands.get("gupao:async");
        try {
            String value = future.get(60, TimeUnit.SECONDS);
            System.out.println("------"+value);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
