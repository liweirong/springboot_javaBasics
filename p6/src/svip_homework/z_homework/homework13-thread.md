1 请说下线程的生命周期！！（语音作业）
2 创建线程的三种方式是什么？手写出3种不同的线程创建方式（线程池除外）
3 interrupt()的作用是什么？？interrupted、isInterrupted、interrupt的区别在哪（语音作业）

4.请描述下JMM内存模型（语音作业）
5 wait、park、sleep区别在哪


1 请说下线程的生命周期！！
线程的状态 

线程在运行过程中，会存在几种不同的状态，一般来说，在Java中，线程的状态一共是6种状态，分别是
NEW：初始状态，线程被构建，但是还没有调用start方法
RUNNABLED：运行状态，JAVA线程把操作系统中的就绪和运行两种状态统一称为“运行中”
BLOCKED：阻塞状态，表示线程进入等待状态,也就是线程因为某种原因放弃了CPU使用权，阻塞也分为几种情况
    Ø 等待阻塞：运行的线程执行wait方法，jvm会把当前线程放入到等待队列
    Ø 同步阻塞：运行的线程在获取对象的同步锁时，若该同步锁被其他线程锁占用了，那么jvm会把当前的线程放入到锁池中
    Ø 其他阻塞：运行的线程执行Thread.sleep或者t.join方法，或者发出了I/O请求时，JVM会把当前线程设置为阻塞状态，当sleep结束、join线程终止、io处理完毕则线程恢复
WAITING: 等待状态
TIME_WAITING：超时等待状态，超时以后自动返回
TERMINATED：终止状态，表示当前线程执行完毕

线程生命周期

    线程的生命周期包含5个阶段，包括：新建、就绪、运行、阻塞、销毁。
    新建：就是刚使用new方法，new出来的线程；
    就绪：就是调用的线程的start()方法后，这时候线程处于等待CPU分配资源阶段，谁先抢的CPU资源，谁开始执行;
    运行：当就绪的线程被调度并获得CPU资源时，便进入运行状态，run方法定义了线程的操作和功能;
    阻塞：在运行状态的时候，可能因为某些原因导致运行状态的线程变成了阻塞状态，比如sleep()、wait()之后线程就处于了阻塞状态，这个时候需要其他机制将处于阻塞状态的线程唤醒，
            比如调用notify或者notifyAll()方法。唤醒的线程不会立刻执行run方法，它们要再次等待CPU分配资源进入运行状态;
    销毁：如果线程正常执行完毕后或线程被提前强制性的终止或出现异常导致结束，那么线程就要被销毁，释放资源;


3 interrupt()的作用是什么？？interrupted、isInterrupted、interrupt的区别在哪（语音作业）

interrupt() 方法用于中断线程，调用该方法的线程的状态为将被置为"中断"状态，可以中断线程的执行了。注意：线程中断仅仅是置线程的中断状态位，不会停止线程。至于什么时候中断，取决于当前线程自己。
    线程通过检查资深是否被中断来进行相应，可以通过isInterrupted()来判断是否被中断。这样中断线程比较优雅
interrupted() 方法的声明为 public static boolean interrupted() 是调用的当前线程的isInterrupted()，并且会重置当前线程的中断状态
isInterrupted() 方法的声明为 public boolean isInterrupted() 是调用该方法的对象所表示的那个线程的isInterrupted()，不会重置当前线程的中断状态

**interrupted 是作用于当前线程，isInterrupted 是作用于调用该方法的线程对象所对应的线程**


5 wait、park、sleep区别在哪
```text
wait要在synchronized里使用是waiting状态，会释放锁，要通过notify或notifyall唤醒；
park是挂起线程，线程处于waiting状态，会释放锁，用unpark唤醒或者intercept打断；
sleep是线程睡眠，线程处于timed_waiting，不释放锁，等待到时间唤醒或者是intercept打断，抛出interception异常

主要从几个方向：1.线程状态 2.会不会释放锁 3.是否要捕捉异常
```
join的原理
```text

wait、synch
```