package svip_homework.F_thread.JUC.condition.modeltest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModelDemo {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        int maxSize = 5;

        Producer producer = new Producer(queue, maxSize, lock, condition);
        Consumer consumer = new Consumer(queue, maxSize, lock, condition);

        Thread consumer1 = new Thread(consumer, "Consumer");
        Thread producer1 = new Thread(producer, "Producer");
        producer1.start();
        consumer1.start();
    }
}
