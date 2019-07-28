package com.iris.springboot_all.work.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author iris
 * @date 2019/7/21
 */
public class ExecutorsLearn implements Runnable {

    public static void main(String[] args) {
        ExecutorService executors = Executors.newFixedThreadPool(3);

        Executors.newCachedThreadPool(); // 伸缩性。。60s回收
        Executors.newScheduledThreadPool(1); // 定时任务
        Executors.newSingleThreadExecutor();


        for (int i = 0; i <100 ; i++) {
            executors.execute(new ExecutorsLearn());
        }
        executors.shutdown();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
