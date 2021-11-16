package svip_homework.F_thread.JUC.reentrantlock.repeatlock;

/**
 * @author iris
 * @create 2021/11/11 18:52
 * 可重入锁
 */
public class RepeatLock {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;

    public synchronized void lock()
            throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
