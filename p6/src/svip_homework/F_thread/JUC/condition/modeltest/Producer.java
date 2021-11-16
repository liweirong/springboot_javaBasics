package svip_homework.F_thread.JUC.condition.modeltest;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {
    private Queue<String> msg;

    private int maxSize;

    Lock lock;
    Condition condition;

    public Producer(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
        this.msg = msg;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            lock.lock();
            while (msg.size() == maxSize) {
                System.out.println("生产者队列满了，先等待");
                try {
                    condition.await();//阻塞线程并释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产消息：" + i);
            msg.add("生产者的消息内容" + i);
            condition.signal();//唤醒阻塞状态下的线程
            lock.unlock();
        }
    }
}
