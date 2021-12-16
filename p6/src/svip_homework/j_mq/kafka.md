
```text
在Kafka文件存储中，同一个topic下有多个不同的partition，每个partition为一个目录，
partition的名称规则为：topic名称+有序序号，第一个序号从0开始计，最大的序号为partition数量减1，partition是实际物理上的概念，而topic是逻辑上的概念。
    drwxr-xr-x 2 root root 4096 Apr 10 16:10 topic_zzh_test-0
    drwxr-xr-x 2 root root 4096 Apr 10 16:10 topic_zzh_test-1
    drwxr-xr-x 2 root root 4096 Apr 10 16:10 topic_zzh_test-2
    drwxr-xr-x 2 root root 4096 Apr 10 16:10 topic_zzh_test-3
上面提到partition还可以细分为segment，这个segment又是什么？如果就以partition为最小存储单位，我们可以想象当Kafka producer不断发送消息，必然会引起partition文件的无限扩张，这样对于消息文件的维护以及已经被消费的消息的清理带来严重的影响，所以这里以segment为单位又将partition细分。每个partition(目录)相当于一个巨型文件被平均分配到多个大小相等的segment(段)数据文件中（每个segment 文件中消息数量不一定相等）这种特性也方便old segment的删除，即方便已被消费的消息的清理，提高磁盘的利用率。每个partition只需要支持顺序读写就行，segment的文件生命周期由服务端配置参数（log.segment.bytes，log.roll.{ms,hours}等若干参数）决定。
segment文件由两部分组成，分别为“.index”文件和“.log”文件，分别表示为segment索引文件和数据文件。
这两个文件的命令规则为：partition全局的第一个segment从0开始，后续每个segment文件名为上一个segment文件最后一条消息的offset值，数值大小为64位，20位数字字符长度，没有数字用0填充，如下：
    00000000000000000000.index
    00000000000000000000.log
    00000000000000170410.index
    00000000000000170410.log
    00000000000000239430.index
    00000000000000239430.log
 
以上面的segment文件为例，展示出segment：00000000000000170410的“.index”文件和“.log”文件的对应的关系，如下图：
 ```