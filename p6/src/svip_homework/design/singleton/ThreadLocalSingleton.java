package svip_homework.design.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author iris
 * @date 2021/10/12
 * 线程单例
 */
@Slf4j
public class ThreadLocalSingleton {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,10,0, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> log.info("获取一个线程单例：threadName:{} \t {}。", Thread.currentThread().getName(), ThreadLocalSingletonBean.getInstance()));
        }
        log.info("2 获取一个线程单例：threadName:{} \t {}。", Thread.currentThread().getName(), ThreadLocalSingletonBean.getInstance());
        new Thread(()->{
            log.info("3 获取一个线程单例：threadName:{} \t {}。", Thread.currentThread().getName(), ThreadLocalSingletonBean.getInstance());
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
    }
}

class ThreadLocalSingletonBean {

    private static ThreadLocal<ThreadLocalSingletonBean> threadLocalInstance = new ThreadLocal<ThreadLocalSingletonBean>() {
        @Override
        protected ThreadLocalSingletonBean initialValue() {
            return new ThreadLocalSingletonBean();
        }
    };


    private ThreadLocalSingletonBean() {

    }

    public static ThreadLocalSingletonBean getInstance() {
        return threadLocalInstance.get();
    }
}

