package svip_homework.F_thread;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author iris
 * @date 2021/11/10
 */
public class ReentrantLockDemo {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock =   reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock =   reentrantReadWriteLock.writeLock();
    private static ReentrantLock noFairReentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {
        // 使用很简单，如下，注意一定要finally释放锁
        try {
            writeLock.lock();
        }finally {
            writeLock.unlock();
        }
        try {
            reentrantLock.lock();
        } finally {
            reentrantLock.unlock();
        }
        try {
            noFairReentrantLock.lock();
        } finally {
            noFairReentrantLock.unlock();
        }

        new Thread(() -> {
            try {
                doSomeThing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                doSomeThing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void doSomeThing() throws InterruptedException {
        try {
            System.out.println(Thread.currentThread().getName() + "进入方法\t" + System.currentTimeMillis());
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁执行中\t" + System.currentTimeMillis());
            Thread.sleep(1000);
        } finally {
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放锁\t" + System.currentTimeMillis());
        }
    }


}
