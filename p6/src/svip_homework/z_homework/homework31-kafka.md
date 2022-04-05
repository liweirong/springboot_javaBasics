1 Kafka怎么保证数据的高可用？（语音作业）<br>
2 Kafka消费者消费消息是pull还是push，为什么？（语音作业）<br>
3 什么是Kafka的Rebalance,什么时候触发？（语音作业）<br>
4 Kafka的分区策略是什么样的？（语音作业）<br>
5 Kafka的消息是有序的么？怎么保证消息的有序性？以及怎么防止消息堆积？（语音作业）<br>

1 Kafka怎么保证数据的高可用？（语音作业）<br>

```text
多副本机制
能保证Kafka的高可用，等同于redis的，但是并不能保证数据丢失！！因为
如果leader宕机，但是leader的数据还没同步到follower上去，此时即使选
举了follower作为新的leader，当时刚才的数据已经丢失了。
为了提高数据安全性，kafka有个ISR机制 in-sync replica ，就是跟leader
partition保持同步的follower partition的数量，只有处于ISR列表中的
follower才可以在leader宕机之后被选举为新的leader，因为在这个ISR列
表里代表他的数据跟leader是同步的。但是也只能降低数据的丢失，如果
要完全保证，必须要同步到所有的replica才代表数据已经提交
```

2 Kafka消费者消费消息是pull还是push，为什么？（语音作业）<br>

```text
pull
本身就是面向大数据的，如果是push的话压力太大
```

3 什么是Kafka的Rebalance,什么时候触发？（语音作业）<br>

```text

```

4 Kafka的分区策略是什么样的？（语音作业）<br>

```text

```

5 Kafka的消息是有序的么？怎么保证消息的有序性？以及怎么防止消息堆积？（语音作业）<br>

```text

kafka 分布式（不是单机）的情况下，如何保证消息的顺 序消费?
参考答案：Kafka 分布式的单位是 partition，同一个 partition 用一个 write ahead log 组织，
所以可 以保证 FIFO 的顺序。不同 partition 之间不能保证顺序。但是绝大多数用户都可以通过 
message key 来定义， 因为同一个 key 的 message 可以保证只发送到同一个 partition。 
Kafka 中发送 1 条消息的时候， 可以指定(topic, partition, key) 3 个参数。
 partiton 和 key 是可选的。如果你指定了 partition，那就是所有消息发往同 1 个 partition，就是有序的。
并且在消费端，Kafka 保证，1 个 partition 只 能 被 1 个 consumer 消费。或者你指定 key（ 比如 order id），具有同 1 个 key 的 所有消息， 会发往同 1 个 partition。








何为顺序性问题？消费者拿到消息以后，但是由于是多个消费者消费，不
确定哪个消费者消费在前，哪个消费在后！
所以本质是多个消费者消费 有顺序性要求的数据！！是并行导致的。
要解决：就只能串行；比如所有相关数据放一个partition，将流量横向
分。
如果要求没那么高，尽量不要去做有序性。
还有个方案，生产者每条消息递增ID，消费端记录当前消息是不是上次的
递增ID；如果不是，塞回去。


这里的消息堆积一般只在mq broker中的消息堆积。那么整个消息堆积，
我们必须知道消息的整个链路。
发送端-broker-消费端
所以方案无非就2个
1.把压力给到发送端 像kafka ,可以加大发送端缓存
2.加快消费端的消费 ， 如果不关心消息顺序，异步多线程处理；如果关心
消息顺序的话
```
