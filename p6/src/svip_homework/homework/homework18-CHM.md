1 请说下对HashMap的理解 (语音作业) <br>
2 ConcurrentHashMap怎么保证保证线程安全？(语音作业)<br>
3 ConcurrentHashMap1.7和1.8的区别(语音作业)<br>

4 为什么容量要设置成2的幂次方 扰动函数

1 请说下对HashMap的理解 (语音作业)

```text
HashMap是一个容器，里面存储key-value类型的数据 ，根据（key，value） 根据key拿到value
HashMap最多只允许一条记录的键为null，允许多条记录的值为null。HashMap非线程安全
底层的数据结构 1.7是数组+链表 1.8之后是优化了链表的查询，满足条件后转红黑树  也会由红黑树转链表、
是一个线程不安全的容器，因为内部没有锁的机制，在并发的情况下put和resize会有线程安全问题，尤其1.7之前还会存在头插法并发扩容导致链表成环导致cpu飙升的情况

put的值的化会依据key的hash来进行定位数组的下标，如果发生hash冲突则会比对value 若值一样就会进行替换value，如果不一样的化就会顺着链表或红黑树进行查询，key相等就替换
    所以这里会要求key一定要有hash方法和equals方法，比如八大基本数据就不能作为key，需要用到其包装类
    1 扩容 达到阈值 0.75n
    2 链表转红黑树 （链长度大于等于8 & 长度大于64） 红黑树转链表（节点小于6）
get方法的化就是依据hash获得index去查询去比较key，相等则返回value，未找到返回空

```

2 ConcurrentHashMap怎么保证保证线程安全？(语音作业)<br>

```text

通过cas和synchronized锁node节点来实现
初始化的时候安全
    如果多个线程同时调用initTable初始化Node数组怎么办
    table变量使用了volatile来保证多线程可见性
    在初始化数组时使用了乐观锁CAS操作来决定到底是哪个线程有资格进行初始化，其他线程均只能让出时间片等待。
    
get方法如何线程安全地获取key、value？
    在get操作中除了增加了迁移的判断以外，基本与HashMap的get操作无异
    值得一提的是这里使用了tabAt方法Unsafe类volatile的方式去获取Node数组中的Node，保证获得到的Node是最新的
    保证能在扩容时也能取到最新的数据
put方法如何线程安全地设置key、value？
       就算有多个线程同时进行put操作，在初始化数组时使用了乐观锁CAS操作来决定到底是哪个线程有资格进行初始化，其他线程均只能等待。
        volatile变量（sizeCtl）：它是一个标记位，用来告诉其他线程这个坑位有没有人在，其线程间的可见性由volatile保证。
        CAS操作：CAS操作保证了设置sizeCtl标记位的原子性，保证了只有一个线程能设置成功
  
reSize方法如何线程安全地扩容？
    如果数据达到一定的条件则会触发扩容在扩容时，ConcurrentHashMap支持多线程并发扩容，在扩容过程中同时支持get查数据，
  若有线程put数据，还会帮助一起扩容-高低位迁移，这种无阻塞算法，将并行最大化的设计

remove方法如何保证线程安全的删除节点？
    自旋和synchronized锁当前节点，当然，如果发现处于一个扩容状态时候也会协助扩容
1.7是锁segment，1.8锁node节点
```

3 ConcurrentHashMap1.7和1.8的区别(语音作业)<br>

```text
1 数据结构
<jdk1.7>：数组（Segment） + 数组（HashEntry） + 链表（HashEntry节点）
    底层一个Segments数组，存储一个Segments对象，一个Segments中储存一个Entry数组，存储的每个Entry对象又是一个链表头结点。
<jdk1.8> ：抛弃segment结构 使用 Node数组 + 链表 + 红黑树

2 并发put时
《1.7》ConcurrentHashMap 使用的分段锁，如果一个线程占用一段，别的线程可以操作别的部分，
《1.8》简化结构，put和get不用二次哈希，一把锁只锁住一个链表或者一棵树，并发效率更加提升。

    <JDK1.7>，
        ConcurrentHashMap（分段锁） 对整个桶数组进行了分割分段(Segment)，每一把锁只锁容器其中一部分数据，多线程访问容器里不同数据段的数据，
        就不会存在锁竞争，提高并发访问率。
    <jdk1.8>
        使用的是优化的synchronized 关键字 和 cas操作了维护并发。
```
