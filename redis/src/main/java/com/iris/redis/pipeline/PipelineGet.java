package com.iris.redis.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: iris
 * @Date: 2019/9/26 21:36
 * @Description:
 */
public class PipelineGet {
    public static void main(String[] args) {
        new Thread(() -> {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            Set<String> keys = jedis.keys("batch*");
            List<String> result = new ArrayList<>();
            long t1 = System.currentTimeMillis();
            for (String key : keys) {
                result.add(jedis.get(key));
            }
            for (String src : result) {
                //System.out.println(src);
            }
            System.out.println("直接get耗时："+(System.currentTimeMillis() - t1));
        },"getThread").start();

        new Thread(() -> {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            //jedis.auth("iris@iris666");
            Set<String> keys = jedis.keys("batch*");
            List<Object> result = new ArrayList<>();
            Pipeline pipelined = jedis.pipelined();
            long t1 = System.currentTimeMillis();
            for (String key : keys) {
                pipelined.get(key);
            }
            result = pipelined.syncAndReturnAll();
            for (Object src : result) {
                //System.out.println(src);
            }
            System.out.println("Pipeline get耗时："+(System.currentTimeMillis() - t1));
        },"pipThread").start();
    }
}
