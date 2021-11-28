1 JVM的参数有哪些，并说明参数作用 <br>
2 你一般会怎么进行JVM调优，怎么找问题并解决问题？（语音作业）

1 JVM的参数有哪些，并说明参数作用

```text
分四大类

1 标准参数 ：跟jdk版本无关
    java -version -help
2 -x参数 ：非标准参数，也就是在JDK各个版本中可能会变动
    -Xint   解释执行
    -Xcomp  第一次使用就编译成本地代码
    -Xmixed 混合模式，JVM自己来决定
3 -xx参数：非标准化参数，相对不稳定，主要用于JVM调优和Debug
    a.Boolean类型  格式：-XX:[+-]<name> +或-表示启用或者禁用name属性
        比如：-XX:+UseConcMarkSweepGC 表示启用CMS类型的垃圾回收器
        -XX:+UseG1GC 表示启用G1类型的垃圾回收器
    b.非Boolean类型 格式：-XX<name>=<value>表示name属性的值是value
        比如：-XX:MaxGCPauseMillis=500 
4 其他参数：也相当于-xx参数
    -Xms1000M等价于-XX:InitialHeapSize=1000M
    -Xmx1000M等价于-XX:MaxHeapSize=1000M
    -Xss100等价于-XX:ThreadStackSize=100
    
```

2 你一般会怎么进行JVM调优，怎么找问题并解决问题？（语音作业）

```text
如果系统中某些指标觉得较差就可以开始调优工作了

首先
1 打印参数信息看系统中关注的信息如堆栈信息，堆中各区的大小及比例
2 打印GC日志，使用GCViewer分析吞吐量、响应时间、回收频率
看系统关注点在哪，如果关注吞吐量、停顿时间的话可以看下垃圾回收器是否设置成期望的


jvm提供了一些工具
jps
jmap dump出堆内存快照在使用工具进行分析
jstat 查看类装载信息、查看gc信息：jstat -gc PID 1000 10
jstack 查看栈信息 
jinfo 实时查看和调整JVM配置参数 \
    1 jinfo -flag <name> PID 查看某个java进程的name属性的值 
    2 jinfo -flag [+|-] PID  |   jinfo -flag <name>=<value> PID ( 参数只有被标记为manageable的flags可以被实时修改)
jvisualvm



```