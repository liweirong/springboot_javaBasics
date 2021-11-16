package svip_homework.F_thread.JUC.reentrantlock.repeatlock;

/**
 * @author iris
 * @create 2021/11/11 18:55
 */
public class Count {
    // 不可重入锁
//    UnRepeatLock lock = new UnRepeatLock();

    // 可重入锁
    RepeatLock lock = new RepeatLock();

    public static void main(String[] args) {
        Count cc = new Count();
        new Thread(() -> {
            try {
                cc.print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void print() throws InterruptedException {
        System.out.println("获取lock");
        lock.lock();
        System.out.println("调用doAdd之前");
        doAdd();
        System.out.println("调用doAdd之后");
        lock.unlock();
        System.out.println("释放lock");
    }

    public void doAdd() throws InterruptedException {
        System.out.println("再次获取lock");
        lock.lock();
        //do something
        lock.unlock();
        System.out.println("再次释放lock");
    }


}
