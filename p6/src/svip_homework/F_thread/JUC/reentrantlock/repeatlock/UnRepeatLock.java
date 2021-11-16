package svip_homework.F_thread.JUC.reentrantlock.repeatlock;

/**
 * @author iris
 * @create 2021/11/11 18:54
 * 不可重入锁
 */
public class UnRepeatLock {
    private boolean isLocked = false;

    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
