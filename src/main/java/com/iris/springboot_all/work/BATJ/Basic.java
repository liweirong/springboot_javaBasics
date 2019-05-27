package com.iris.springboot_all.work.BATJ;

/**
 * 每天一个小知识点
 * 后期存到数据库，方便刷题，分类，进行抽样生成试卷
 * 知识点表，题库表  一个题对应一类知识点，一个知识点也可以对应一类题
 * 前台抽样，选择题提供正确答案，判断题，算法、编程题提供思路及代码自己对比，解决方案题多总结
 *
 *
 *
 * @author iris
 * @date 2019/5/20
 */
public class Basic {

    // 1.析构函数
    /**
     * 析构函数(destructor) 与构造函数相反，当对象结束其生命周期，如对象所在的函数已调用完毕时，系统自动执行析构函数。
     * 在java中，我们一般用不到它，因为 java 有自动内存回收机制，无须程序员手动释放。java 对象析构时会调用 finalize() 方法。
     * 那么finalize()究竟是做什么的呢？它最主要的用途是回收特殊渠道申请的内存。Java程序有垃圾回收器，所以一般情况下内存问题不用程序员操心。
     * 但有一种JNI(Java Native Interface)调用non-Java程序（C或C++），finalize()的工作就是回收这部分的内存。
     */
    //事务  特性 ACID ;2事务隔离级别

    // mysql引擎:innoDb引擎和myISAM引擎对比

    /**innodb锁机制：共享锁(S锁)、排他锁（X锁）  意向共享锁（IS锁），意向排他锁（IX锁）
     *锁算法： 临键锁（next key）、间隙锁（gap锁） 、记录锁（record）
     *
     * innodb中通过：mvcc + next key解决幻读
     *
     */
    // undo log（保证事务原子性）、 redo log（保证事务的持久性）
}
