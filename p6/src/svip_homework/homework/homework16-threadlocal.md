1 请画出ThreadLocal结构图<br>
2 为什么ThreadLocal的key设置成弱引用，而value不设置成弱引用（语音作业）<br>
3 ThreadLocal内存泄露怎么解决的？怎么解决hash冲突？（语音作业）<br>


<br>
*ThreadLocal*是除了加锁这种同步方式之外的一种保证一种规避多线程访问出现线程不安全的方法，当我们在创建一个变量后，如果每个线程对其进行访问的时候访问的都是线程自己的变量这样就不会存在线程不安全问题。
ThreadLocal是一个本地线程副本变量工具类。主要用于将私有线程和该线程存放的副本对象做一个映射，各个线程之间的变量互不干扰，
在高并发场景下，可以实现无状态的调用，特别适用于各个线程依赖不同的变量值完成操作的场景。


1 请画出ThreadLocal结构图

```text
    Thread.class
    ThreadLocal.ThreadLocalMap threadLocals = null;
ThreadLocalMap是ThreadLocal的内部类，没有实现Map接口，用独立的方式实现了Map的功能，其内部的Entry也独立实现
    
```

2 为什么ThreadLocal的key设置成弱引用，而value不设置成弱引用（语音作业）

```text
为什么ThreadLocalMap中把ThreadLocal对象存储为Key时使用的是弱引用
    一般来说使用ThreadLocal时会有两个引用指向ThreadLocal对象，
    一个是创建ThreadLocal对象时的显式的引用（下文称为显式引用），
    还有一个就是ThreadLocalMap对ThreadLocal对象的弱引用。
当我们不再使用ThreadLocal时，显式引用不再指向ThreadLocal对象。
这时只有ThreadLocalMap对ThreadLocal对象的弱引用存在。

假设ThreadLocalMap对ThreadLocal的引用是强引用
    由于ThreadLocalMap是属于线程的，而我们创建多线程时一般是使用线程池进行创建，线程池中的部分线程在任务结束后是不会关闭的，
    那么这部分线程中的ThreadLocalMap将会一直持有对ThreadLocal对象的强引用，导致ThreadLocal对象无法被垃圾回收，从而造成内存泄漏。
设置成弱引用
    在下一次垃圾回收时，无论内存空间是否足够，只有弱引用指向的对象都会被直接回收。
    所以将ThreadLocalMap对ThreadLocal对象的引用设置成弱引用，就能避免ThreadLocal对象无法回收导致内存泄漏的问题。
    但是ThreadLocalMap对value的引用是强引用，所以value部分还是有内存泄漏的可能。
    所以ThreadLocal类定义了expungeStaleEntry方法用于清理key为null的value。
        但是expungeStaleEntry并不能清理完全部的null，比如成为null后不进行get set调用就无法清除，
        而且就算调用，根据线性嗅探只会嗅探到空节点就停了，可能存在分段的空节点
    expungeStaleEntry在remove中方法中调用。


```

3 ThreadLocal内存泄露怎么解决的？怎么解决hash冲突？（语音作业）

```text
---- ThreadLocal内存泄露怎么解决的？
* 首先知道内存泄露是什么？
        是指程序中已动态分配的堆内存由于某种原因程序未释放或无法释放，造成系统内存的浪费，导致程序运行速度减慢甚至系统崩溃等严重后果

* 怎么发生的？
    由于ThreadLocalMap的key是弱引用，而Value是强引用。这就导致了一个问题，ThreadLocal在没有外部对象强引用时，发生GC时弱引用Key会被回收，而Value不会回收，
如果创建ThreadLocal的线程一直持续运行，比如线程池，那么这个Entry对象中的value就有可能一直得不到回收，发生内存泄露。
* 怎么解决
        既然Key是弱引用，那么我们要做的事，就是在调用ThreadLocal的get()、set()方法时完成后再调用remove方法，将Entry节点和Map的引用关系移除，
    这样整个Entry对象在GC Roots分析后就变成不可达了，下次GC的时候就可以被回收。
    如果使用ThreadLocal的set方法之后，没有显示的调用remove方法，就有可能发生内存泄露，所以养成良好的编程习惯十分重要，使用完ThreadLocal之后，记得调用remove方法。



--- 怎么解决hash冲突？
与HashMap不同，ThreadLocalMap结构非常简单，就是单个节点（无链表、红黑树），没有next引用
也就是说ThreadLocalMap中解决Hash冲突的方式并非链表的方式，而是采用线性探测的方式。
    所谓线性探测，就是根据初始key的hashcode值确定元素在table数组中的位置，如果发现这个位置上已经有其他key值的元素被占用，则利用固定的算法寻找一定步长的下个位置，依次判断，直至找到能够存放的位置。
    ThreadLocalMap解决Hash冲突的方式就是简单的步长加1或减1，寻找下一个相邻的位置。
    ThreadLocalMap采用线性探测的方式解决Hash冲突的效率很低，如果有大量不同的ThreadLocal对象放入map中时发送冲突，或者发生二次冲突，则效率很低。
所以这里引出的良好建议是：每个线程只存一个变量，这样的话所有的线程存放到map中的Key都是相同的ThreadLocal，
如果一个线程要保存多个变量，就需要创建多个ThreadLocal，多个ThreadLocal放入Map中时会极大的增加Hash冲突的可能
```