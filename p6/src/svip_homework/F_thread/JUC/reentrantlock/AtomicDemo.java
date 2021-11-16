package svip_homework.F_thread.JUC.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author iris
 * @create 2021/11/11 17:46
 */
public class AtomicDemo {
    private static int count = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                AtomicDemo.inc();
            }).start();
        }
        Thread.sleep(4000);
        System.out.println(count);
    }

    public static void inc() {
        lock.lock();
        try {
            Thread.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void dec() {
        lock.lock();
        try {
            Thread.sleep(1);
            count--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
