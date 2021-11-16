package svip_homework.F_thread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo01 implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptDemo01());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {//获取中断标记，默认是false
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("正常执行");
            } catch (InterruptedException e) {
                //复位 false
                Thread.currentThread().interrupt();//true
                System.out.println("1"+Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();//true
                System.out.println("2"+ Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
            System.out.println("线程循环");
        }
        System.out.println("结束");
    }


}
