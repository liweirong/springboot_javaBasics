package svip_homework.i_redis.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import java.util.List;

/**
 /**
 * iris
 * 事务的四大命令MULTI EXEC DISCARD WATCH
 */
public class TestTransaction {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                Jedis jedis = new Jedis("192.168.44.181", 6379);
                String watch = jedis.watch("trxkey");
                System.out.println("method1线程["+Thread.currentThread().getName()+"]watch结果："+watch);
                Transaction multi = jedis.multi();
                multi.set("trxkey", "2673-thread1");
                // 让Thread2先执行完
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Object> exec = multi.exec();
                System.out.println("method1执行结果："+exec);
                jedis.unwatch();
            }
        }.start();

        new Thread(){
            public void run(){
                Jedis jedis = new Jedis("192.168.44.181", 6379);
                String watch = jedis.watch("trxkey");
                System.out.println("method2线程["+Thread.currentThread().getName()+"]watch结果："+watch);
                Transaction multi = jedis.multi();
                multi.set("trxkey", "2673-thread2");
                List<Object> exec = multi.exec();
                System.out.println("method2执行结果："+exec);
            }
        }.start();

    }





}
