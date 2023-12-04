1 简述线程池的好处（语音作业）<br>
2 线程池的核心参数有哪些，分别表示什么意思?（语音作业）<br>
3 请画出线程池的实现流程（画），并说出来（语音作业）<br>


1 简述线程池的好处（语音作业）
```text
首先为啥会出现线程池 没有线程池的化我们都是new一个个thread，
    1 频繁进行创建销毁 2 缺乏管理 可能无限制创建线程 3 功能单一，比如定时 定期


池化的好处 
1 可以降低资源的消耗，通过重复使用已经创建的线程，可以降低创建线程和销毁线程带来的消耗；
2 提高响应速度，当任务到达时，池子里有创建好的线程，不需要等待线程创建就能立即执行；
3 提高线程的可管理性，通过线程池，可以对线程进行统一的分配，调优和监控。
```
2 线程池的核心参数有哪些，分别表示什么意思?（语音作业）

```text

七大核心参数
public ThreadPoolExecutor(int corePoolSize, // 核心线程数
int maximumPoolSize, // 最大线程数
long keepAliveTime, // 空闲线程过多久被回收
TimeUnit unit, // 单位
BlockingQueue<Runnable> workQueue, // 阻塞队列
ThreadFactory threadFactory, // 线程工厂
RejectedExecutionHandler handler) { // 拒绝策略
```
3 请画出线程池的实现流程（画），并说出来（语音作业）