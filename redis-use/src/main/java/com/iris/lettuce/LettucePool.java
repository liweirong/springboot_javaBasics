package com.iris.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author: iris
 * @Date: 2019/10/15 11:45
 * @Description:
 */
public class LettucePool {
    public static void main(String[] args) throws Exception {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        // 使用连接池
        GenericObjectPool<StatefulRedisConnection<String, String>> pool = ConnectionPoolSupport
                .createGenericObjectPool(() -> client.connect(), new GenericObjectPoolConfig());

        try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) {
            RedisCommands<String, String> commands = connection.sync();
            commands.set("iris", "2673");
            System.out.println(commands.get("iris"));
            commands.zadd(" iris", 100, "Java");
            commands.zadd(" iris", 80, "Python");
            System.out.println("---------------------"+commands.zcard(" iris"));
        }
        pool.close();
        client.shutdown();
    }
}
