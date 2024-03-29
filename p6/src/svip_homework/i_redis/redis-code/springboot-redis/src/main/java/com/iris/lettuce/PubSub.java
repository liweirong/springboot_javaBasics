package com.iris.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * @author  iris
 */
public class PubSub {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://192.168.44.181:6379");

        // connection
        RedisPubSubListener<String, String> listener = new RedisPubSubListener<String, String>() {
            @Override
            public void message(String pattern, String channel) {
                System.out.println("message:" + pattern + "," + channel);
            }

            @Override
            public void message(String pattern, String channel, String message) {
                System.out.println("message:" + pattern + "," + channel + "," + message);
            }

            @Override
            public void psubscribed(String pattern, long count) {
                System.out.println("psub:" + pattern + "," + count);
            }

            @Override
            public void punsubscribed(String pattern, long count) {
                System.out.println("punsub:" + pattern + "," + count);
            }

            @Override
            public void subscribed(String channel, long count) {
                System.out.println("sub:" + channel + "," + count);
            }

            @Override
            public void unsubscribed(String channel, long count) {
                System.out.println("ubsub:" + channel + "," + count);
            }
        };

        StatefulRedisPubSubConnection<String, String> pubSubConnection = client.connectPubSub();

        pubSubConnection.addListener(listener);

        RedisPubSubCommands<String, String> connection = pubSubConnection.sync();
        connection.subscribe("news-gupao");

        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pubSubConnection.close();
        client.shutdown();
    }
}