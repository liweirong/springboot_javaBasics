package svip_homework.F_thread;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author iris
 * @date 2021/11/6
 */
public class SynchronizedDemo {
    public volatile static int i = 0;
    public static int j = 0;
    public static int z = 0;

    public static void main(String[] args) throws InterruptedException {
        LockDemo lockDemo = new LockDemo();
        // 无锁态 00000001
        System.out.println("加锁之前\n" + ClassLayout.parseInstance(lockDemo).toPrintable());
        synchronized (lockDemo) {
            //偏向锁默认延迟加载 所以直接轻量级锁     00001000 轻量级锁
            System.out.println("加锁之后\n" + ClassLayout.parseInstance(lockDemo).toPrintable());
        }


        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            cachedThreadPool.submit(() -> {
//                System.out.println("通过线程池执行1创建线程名称：" + Thread.currentThread().getName() + "，执行");
                demoMethod1();
            });
        }
        for (int i = 0; i < 1000; i++) {
            cachedThreadPool.submit((Runnable) SynchronizedDemo::demoMethod2);
        }
        for (int i = 0; i < 1000; i++) {
            cachedThreadPool.submit((Runnable) SynchronizedDemo::demoMethod3);
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(i); // 982 1000 996
        System.out.println(j); // 1000
        System.out.println(z); // 100


    }

    /**
     * 修饰非静态方法
     *
     * @return
     */
    private synchronized int demoMethod() {
        return i++;
    }

    /**
     * 无锁
     *
     * @return
     */
    private static int demoMethod1() {
        return i++;
    }

    /**
     * 修饰静态方法
     *
     * @return
     */
    private static synchronized int demoMethod2() {
        return j++;
    }

    /**
     * 修饰代码块
     *
     * @return
     */
    private static int demoMethod3() {
        synchronized (SynchronizedDemo.class) {
            return z++;
        }
    }

}

class LockDemo {

}