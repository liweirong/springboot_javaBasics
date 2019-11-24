package com.iris.redis.pipeline;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @Author: iris
 * @Date: 2019/9/26 21:42
 * @Description:
 */
public class PipelineSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Pipeline pipelined = jedis.pipelined();
        long t1 = System.currentTimeMillis();
        for (int i=0; i < 100000; i++) {
            pipelined.set("batch"+i,""+i);
        }
        pipelined.syncAndReturnAll();
        long t2 = System.currentTimeMillis();
        System.out.println("耗时："+(t2-t1)+"ms");// 1213ms
    }
}
