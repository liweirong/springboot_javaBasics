package svip_homework.F_thread;


import java.util.concurrent.*;

/**
 * @author iris
 * @date 2021/11/16
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        // [可缓存线程池]，先查看池中有没有以前建立的线程，如果有，就直接使用。如果没有，就建一个新的线程加入池中，缓存型池子通常用于执行一些生存期很短的异步型任务
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        // 创建一个可重用[固定线程数]的线程池，以共享的无界队列方式来运行这些线程(无任务也不销毁)
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        // 创建一个定长线程池，支持[定时执行]
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 创建一个[单线程化的线程池]，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // 1.8新增  性能不高
        ExecutorService workStealingPool = Executors.newWorkStealingPool();

        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new MyTask());
        }


        // -------------------七大核心参数--------------------
        //        public ThreadPoolExecutor(int corePoolSize,
        //        int maximumPoolSize,
        //        long keepAliveTime,
        //        TimeUnit unit,
        //        BlockingQueue<Runnable> workQueue,
        //        ThreadFactory threadFactory,
        //        RejectedExecutionHandler handler


        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 3,
                10, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(3),
                new ExecJavaTemplate());
        // 允许核心线程数超时
        executorService.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行任务");
            }));
        }
    }
}

class MyTask implements Runnable {

    @Override
    public void run() {
        System.out.println("myTask doing");
    }
}

/**
 * 拒绝策略
 */
class ExecJavaTemplate implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("拒绝策略拒绝");
    }
}