package svip_homework.F_thread;

/**
 * @author iris
 * @date 2021/11/9
 */
public class ThreadLocalDemo {
    static ThreadLocal<Integer> threadLocalA = new ThreadLocal<>();
    static ThreadLocal<Integer> threadLocalB = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (Thread thread : threads) {
            thread = new Thread(() -> {
                threadLocalA.set(1);
                threadLocalA.get();
                threadLocalB.get();
                System.out.println(Thread.currentThread().getName() + " threadLocal :" + threadLocalB.get());
                threadLocalA.remove();
                threadLocalB.remove();
            });
            thread.start();
        }
    }
}
