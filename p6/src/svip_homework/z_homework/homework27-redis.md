1 请例举Redis常用的数据类型以及使用场景（语音作业）<br>
2 请说明Redis实现分布式锁的方式有哪些？为什么可以做分布式锁？（语音作业）<br>

1 请例举Redis常用的数据类型以及使用场景（语音作业）<br>

```text
官网提供了八大，但是常用的是5大类型
string list hash  set zset stream Geospatial Hyperloglog


    string	简单的key-value数据结构,因为提供了一些自增、setnx等命令，可以做一些热点数据的存储、分布式ID、计数器、限速分布式锁、单点登录session缓存
	hash	key-field-value，可以存结构化的数据，存一张表的数据。用来做购物车（用户id做key 物品id做field value做数量）、对象的缓存，只能针对key设置过期时间
		当哈希类型元素个数小于hash-max-ziplist-entries配置（默认512个）同时所有值都小于hash-max-zipl ist-value配置（默认64字节）时使用ziplist,否则用hashtable
	list	key-双向链表，用来做消息队列、文章列表 模拟栈 lpush lpop 
		当哈希类型元素个数小于list-max-zip list-entries配置（默认512个）同时所有值都小于list-max-zip list-value配置（默认64字节）时使用ziplist,否则用linkedlist
	set		key-set集合，因为是集合，所以有提供交集并集这样的指令，可以用来打标签(点赞)、抽奖、可能认识的人等功能
		当集合中的元素都是整数且元素个数小于set-max-intset-entries配置（默认512个）时，redis会选用intset来作为集合的内部实现，从而减少内存的使用,否则hashtable
	zset	key-zset，有序且不可重复的集合，多了score分值。用来做排序、热榜、延迟消息队列功能
		当有序集合的元素个数小于list-max-ziplist-entries配置（默认128个）同时所有值都小于list-max-ziplist-value配置（默认64字节）时使用ziplist,否则skiplist
	Hyperloglog	用来做基数统计，计算 2^64 个不同元素的基数，固定空间12KB。网页统计用户浏览量 pv
	Geospatial	存储位置信息，可以很方便计算和管理位置信息，用来做附近的人
	Streams		消息队列
```

2 请说明Redis实现分布式锁的方式有哪些？为什么可以做分布式锁？（语音作业）<br>

```text
1.setnx+lua脚本,基于redis的单线程命令执行
2.redission封装的分布式锁，底层也属于lua脚本

锁的必要条件是互斥 - redis是单线程的，不存在并发，所以可以拿到互斥条件
```