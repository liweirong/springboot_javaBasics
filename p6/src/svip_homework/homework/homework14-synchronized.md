1 什么是线程安全问题，Synchronized加锁的范围有哪些?不同的范围有哪些实现方式（语音作业）<br/>
2 写出Synchronized不同加锁方式demo。<br/>
3 请描述下Synchronized的锁升级。（语音作业）<br/>
4 请描述下线程的通信方式有哪些？区别在哪里（语音作业）<br/>

1 什么是线程安全问题，Synchronized加锁的范围有哪些?不同的范围由哪些实现方式 ？<br/>

```
当多个线程同时共享，同一个全局变量或者静态变量，做写的操作时，可能会发生数据冲突问题，也就是线程安全问题。最后结果导致程序得不到期望的结果，
做读操作时不会发生数据冲突问题的。 

线程安全问题包括：原子性问题、可见性问题以及有序性问题。

Synchronized加锁的范围有 对象锁、类锁;

不同的范围由哪些实现方式？
方法锁：加在普通方法上面，加载普通方法代码块里
类锁：加在静态方法上，锁对象是类对象

- 修饰实例方法： 作用范围是当前实例
- 修饰静态方法；作用范围是当前类
- 修饰代码块。 作用范围取决于锁对象本身作用域
```

3 请描述下Synchronized的锁升级。（语音作业）<br/>

```txt
Jdk1.6对锁的实现引入了大量的优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销。
        锁主要存在四中状态，依次是：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，他们会随着竞争的激烈而逐渐升级。
        这么设计的目的，其实是为了减少重量级锁带来的性能开销，尽可能的在无锁状态下解决线程并发问
        题，其中偏向锁和轻量级锁的底层实现是基于自旋锁，它相对于重量级锁来说，算是一种无锁的实现。
        用户态和内核态切换耗性能
        
        用户态：程序内-用户空间；
        内核态：内核发起指令
        
        无锁 ：
        偏向锁 ：第一次A进入同步块无竞争抢占到了锁，作为偏向锁，以后A进来不需要抢占锁，直接获得锁执行。实际应用默认延迟开启或关闭
        轻量级锁 ：在偏向锁的基础上，当多个线程抢占锁的时候，B进入发现偏向A；进行升级到轻量级锁。通过自旋来解决
                偏向锁和轻量级锁都是无锁话的实现，此时的线程处于运行状态，没有线程阻塞
        重量级锁 ：未抢到锁进行阻塞
        
        锁升级的过程不会逆转
       CAS : 所有锁升级会涉及到修改锁标记位，修改指向线程信息，是通过cas实现的 cas底层还是会用到cpu层面的锁
```

4 请描述下线程的通信方式有哪些？区别在哪里（语音作业）<br/>

```text
首先明确线程为什么要通信？通信的目的是为了更好的协作，线程无论是交替式执行，还是接力式执行，都需要进行通信告知

内存共享 Volatile  JUC下面的包里面也有很多工具类可以实现
    基于volatile关键字来实现线程间相互通信是使用共享内存的思想，大致意思就是多个线程轮询同时监听同一个变量，当这个变量发生变化的时候，
    线程能够感知变化并执行相应的业务。这也是最简单的一种实现方式，条件变量始终与互斥锁一起使用
Object类中的wait/notify/notifyall 
    wait和 notify必须配合synchronized使用，wait方法释放锁，notify方法不释放锁
park/unpark
    LockSupport.park(); // 暂停当前线程
    LockSupport.unpark(暂停线程对象); // 恢复某个线程的运行
    park函数是将当前调用Thread阻塞，而unpark函数则是将指定线程Thread唤醒。
    是一种非常灵活的实现线程间阻塞和唤醒的工具，使用它不用关注是等待线程先进行还是唤醒线程先运行，但是得知道线程的名字。
```