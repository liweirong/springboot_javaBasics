package svip_homework.F_thread.JUC.reentrantlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author iris
 * @create 2021/11/11 18:35
 */
public class ReadWriteDemo {

    static Map<String, Object> cacheMap = new HashMap<String, Object>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock read = rwl.readLock();
    static Lock write = rwl.writeLock();


    public static void main(String[] args) {
        new Thread(() -> {
            ReadWriteDemo.write("张三", "18");
        }).start();
        new Thread(() -> {
            ReadWriteDemo.write("张三", "20");
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println(ReadWriteDemo.get("张三"));
        }).start();
        new Thread(() -> {
            System.out.println(ReadWriteDemo.get("张三"));
        }).start();

    }

    public static Object get(String key) {
        read.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            return cacheMap.get(key);
        } finally {
            read.unlock();
        }
    }

    public static Object write(String key, Object value) {
        write.lock();
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return cacheMap.put(key, value);
        } finally {
            write.unlock();
        }
    }

}
