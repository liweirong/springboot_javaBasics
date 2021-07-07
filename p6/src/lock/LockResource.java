package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author iris
 * @date 2021/6/23
 */
public class LockResource {

    private static Lock lock = new ReentrantLock();
    private static int t = 0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100 ; i++) {
            new Thread(()->{
                lock.lock();
                t++;
                lock.unlock();
            }).start();
        }

        Thread.sleep(1000);
        System.out.println(t);

    }

    public void t() {
        LockSupport.park(this);
    }



}
