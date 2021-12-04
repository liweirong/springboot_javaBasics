请描述下一条写sql的执行流程（语音作业）<br>
讲下你对MySQL三大日志的理解（语音作业）<br>

请描述下一条写sql的执行流程（语音作业）<br>

```text
现在一般用到引擎默认是innodb
sql语句进来会先到连接层建立链接，进行权限等的校验，再走到server层，进行词法解析、语法解析、这时走到buffer pool

会出现两个情况 1 buffer pool中有此数据的记录，只需要更改buffer pool的数据页即可；如果没有则会把磁盘数据加载到buffer pool
        这个在内存被更改的页也就叫做脏页，后续脏页要进行刷盘，也就是刷脏
        刷脏在哪？ 这些脏页会在我们buffer pool里面有一个专门的脏页链表，叫做flush链表。这样我去找我的脏页只要去这个链表找就行，不需要在我内存的所有的页面里面去找了。
        如何刷？ 
            1 专门的线程每隔一段时间就把脏页刷新到磁盘
        为了保证页的数据一致性，又有了 Double Write的机制
            1.在刷盘覆盖磁盘的时候，我们会将page内容写入到磁盘的另外一个地方，叫做double write buffer。
            2.然后再把内存里面的数据同步到磁盘，如果第一步失败，原来的数据没有覆盖，数据页是完整的，如果第二步失败，原来的数据不完整，但是double write buffer是完整的，可以用double write buffer里面的覆盖

三大日志的写入的顺序是，当更新的sql语句进来时
    1 undo log会记录一个反向操作的日志
    2 redo log会变成prepare 
    3 记录bin log
    4 redo log状态变为commit
其中redo log undo log 是存在磁盘，会根据配置进行刷盘到此磁盘
```

讲下你对MySQL三大日志的理解（语音作业）<br>

```text
bin log 、redo log、undo log
bin log 是server层日志，binlog是我们的二进制日志文件，记录了Mysql的所有的DML操作，我们可以通过binlog日志做数据恢复、增量备份、主从复制等！
redo log 和undo log时innodb引擎独有，我们知道innodb有几个特性、其中有支持崩溃恢复、事务..

redo log 是引擎层日志，innodb独有，用来支持崩溃恢复的。刷脏的时候电脑崩溃会导致数据丢失；保证持久性；
    redo log大小固定，默认会有两个文件，每个文件48M，所以前面的数据会被覆盖掉
    redo log又有两个部分：redo log Buffer、redo log file 磁盘日志文件
    redo log何时从buffer存到磁盘？
        正常关机
        后台线程异步执行
        事务提交时，可配 innodb_flush_log_at_trx_commit参数 该参数有几个选项：0、1、2
            0：每秒写一次日志并将其刷新到磁盘-极限情况会丢失一秒的数据。
            1：表示当你commit时，MySQL必须将redo log-buffer中的数据刷新进磁盘中。确保只要commit是成功的，磁盘上就得有对应的redo log日志。这也是最安全的情况。
            2：表示当你commit时，将redo log-buffer中的数据刷新进OS Cache中，然后依托于操作系统每秒刷新一次的机制将数据同步到磁盘中，也存在丢失的风险。
    在崩溃恢复时，判断事务是否需要提交：
        1、binlog无记录，redo log无记录：在redo log写之前crash，恢复操作：回滚事务
        2、binlog无记录，redo log状态prepare：在binlog写完之前的crash，恢复操作：回滚事务
        3、binlog有记录，redo log状态prepare：在binlog写完提交事务之前的crash，恢复操作：提交事务
        4、binlog有记录，redo log状态commit： 正常完成的事务，不需要恢复
undo log 是引擎层日志，是用来做事务日志；记录了事务之前的数据状态，一旦修改发生异常进行回滚；保证原子性

redo log 和 undo log因为和事务有关所以统称为事务日志；
redo log,undo log是逻辑日志 、bin log是物理日志，记录具体的sql

三大日志的写入的顺序是，当更新的sql语句进来时
    1 undo log会记录一个反向操作的日志
    2 redo log会变成prepare 
    3 记录bin log
    4 redo log状态变为commit

```