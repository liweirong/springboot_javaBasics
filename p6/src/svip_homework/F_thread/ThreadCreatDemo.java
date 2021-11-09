package svip_homework.F_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author iris
 * @date 2021/11/5
 * 创建线程的三种方式是什么？手写出3种不同的线程创建方式
 */
public class ThreadCreatDemo {

    private static int i;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Thread1 thread1 = new Thread1();
        thread1.start();

        Thread thread2 = new Thread(new Thread2());
        thread2.start();

        FutureTask<String> future = new FutureTask<>(new Thread3(-1));
        Thread thread3 = new Thread(future, "有返回值的线程");
        thread3.start();
        System.out.println(thread3.getName() + "执行回调响应：" + future.get());


        // -------------------------------------------------------------------------//
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(() -> System.out.println("通过线程池执行创建线程名称：" + Thread.currentThread().getName() + "，执行"));


        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future2 = executorService.submit(new Thread3(i));
            //将任务执行结果存储到List中
            resultList.add(future2);
        }
        System.out.println("result:" + resultList.get(0).get());


        System.out.println("---------------线程中断--------------------");

        Thread thread = new Thread(() -> {
            // 默认情况下 isInterrupted返回false、通过thread.interrupt变成了true
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Num:" + i);
        }, "interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1 thread.isInterrupted():" + thread.isInterrupted() + " thread.interrupted();" + thread.interrupted());
        thread.interrupt(); // 加和不加的效果
        System.out.println("调用 thread.interrupt()");
        System.out.println("2 thread.isInterrupted():" + thread.isInterrupted() + " thread.interrupted();" + thread.interrupted());
        System.out.println("3 thread.isInterrupted():" + thread.isInterrupted() + " thread.interrupted();" + thread.interrupted());


    }
}

/**
 * 继承thread
 */
class Thread1 extends Thread {

    @Override
    public void run() {
        System.out.println("执行继承thread创建的线程");
    }
}

/**
 * 实现Runnable接口
 */
class Thread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("执行实现Runnable接口创建的线程");
    }
}

/**
 * 实现Callable接口 配合Future 实现带返回值的线程
 */
class Thread3 implements Callable<String> {
    private Integer i;

    public Thread3(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        System.out.println("通过实现Callable接口获取数据" + i);
        return "Callable执行成功 " + i;
    }
}