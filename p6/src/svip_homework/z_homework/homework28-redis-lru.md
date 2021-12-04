Redis有哪些过期策略，有哪些淘汰策略，请详细说下它们的实现！（语音作业）<br>
Redis持久化的方式有哪些，有什么区别（语音作业）<br>

Redis有哪些过期策略，有哪些淘汰策略，请详细说下它们的实现！（语音作业）<br>
```text
redis是内存数据库，和jvm一样也要对内存进行管理，随着系统的使用也会满
所以为了解决满的问题redis提供了过期策略和淘汰策略
    过期策略为了回收垃圾、淘汰策略是当内存满了的时候不至于无法使用
过期策略
    定时过期 定时每隔一段时间去扫一部分数据，然后清除其中过期的数据，（可配置执行时间和拿到的个数进行调整cpu和内存的关系）
            redis默认100ms去扫一次，按桶的维度拿到至少20个数据进行check删除
            具体实现方式为：
            1. 不是一次性把所有设置了过期时间的数据拿出来，而是按hash桶维度取里面取值，取到20个值为止，如果第一个有30个，那么也会取30个！如果一直取不到20，那么最多400个桶
            2. 删除取出值的过期key
            3. 如果400个桶都取不到值，或者取出的key 删除的比例大于10%,继续上面的操作
            4. 每循环16次会去检测时间，超过指定时间就跳出
    惰性删除 查询到当前key的时候进行删除，此时会有一个问题，如果都不去读的话就删不掉
redis是两种一起配合使用的

如果redis存的数据都没过期，慢慢满了之后又会导致溢出，此时redis为了避免系统不可以设计了淘汰策略

淘汰策略
    LRU         最近最少使用 redisObject.lru存了最近使用的时间的s单位的低24位，只能存194天，因为不是存的所有的，不知道过了几轮，所以是个伪LRU
                     配置的采样值默认5个，随机从库中取m个，淘汰其中热度最低的key
      
    LFU         最近最少使用-redis解决了单纯lfu对于失效性的不足，同样redisObject.lru里的高16位存了最近使用分单位的低16位，低八位存了次数（255）
                redis的配置里面可以配置参数进行调整，比如每过一分钟就会进行-1保证不会某些key历史使用频繁而一直占据热搜榜
    不删除       不进行添加，只给查询（默认）
    
    删除将要过期的键 
    random      随机删除
redis提供了八大配置在redis.conf中可进行配置
    # volatile-lru -> Evict using approximated LRU, only keys with an expire set.
    # allkeys-lru -> Evict any key using approximated LRU.
    # volatile-lfu -> Evict using approximated LFU, only keys with an expire set.
    # allkeys-lfu -> Evict any key using approximated LFU.
    # volatile-random -> Remove a random key having an expire set.
    # allkeys-random -> Remove a random key, any key.
    # volatile-ttl -> Remove the key with the nearest expire time (minor TTL)
    # no eviction -> Don't evict anything, just return an error on write operations.（default）
```
Redis持久化的方式有哪些，有什么区别（语音作业）<br>
```text

持久话的方式有AOF、RDB

aof 采用日志的形式记录每个写操作的 .aof  默认是不开启的 
    可以在redis.conf里配置开启，并发大的时候会造成文件很大
    文件持久化时机：
        no          由操作系统保证-执行最快
        always      同步执行，效率低
        everysec    每秒进行持久化（默认）兼顾了安全性和效率，丢数据也就丢1s
    aof文件过长的话会自动进行重写，相关参数也是可配置的；也可以手动指令：bgrewriteof
RDB 是redis内存数据的快照， .rdb
    文件持久化时机：
        定期保存-可能会有数据丢失
        自动触发：配置文件配置多少秒有多少个key进行变化的话再写
            save 900 1 #900秒内如果超过1个key被修改，则发起快照保存。
            save 300 10 #300秒内如果超过10个key被修改，则快照保存
        手动触发：调用save bgsave进行手动生成 - save会阻塞其他指令，生产别用，所以提供了bgsave，他是进行了fork了一个子进程进行操作，持久化由fork子进程执行，阻塞的时间是fork的时间
        正常关机
        flashAll
    劣势：无法做到秒级的持久化，所以可能会丢失间隔时间内的数据；bgsave每次都会fork子进程，频繁执行成本高
在进行备份恢复数据量大的时候RDB会快些，因为不用执行全部指令
相同数据量的情况下，aof的文件会比RDB文件大
redis.conf开启aof之后默认就是采用aof来进行恢复

一般的话两种持久化方案一起使用，取各自的优势
重启载入AOF文件来恢复数据，因为aof保存的文件集会比rdb文件更完整
```