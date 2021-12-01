DDL(Data Definition Language) ALTER 、CREATE
DML(Data Manipulation Language) SELECT、 INSERT
DCL（Data Control Language） 数据控制语言 COMMIT

索引的结构
B+树

聚簇索引 - 主键索引，没有的话就会选定一个唯一索引，再没有就默认一个唯一索引
    数据节点存在叶子节点，叶子节点的每个节点是个双向链表；上层节点作为索引目录 记录下一个节点的最小值和页地址
    因为目录页不存数据，只存主键和下一层的页地址 所以能存很多路，存满16K一页，这样进行查找的时候能在根节点一路找下来，所以这里说明了为什么唯一：能找到值

页分裂：每一个页的数据是按顺序排列的，如果插入一个的话，存储引擎会将此页进行分裂，导致存储空间增多 ；这就是为什么主键索引需要自增或趋势递增-减少页分裂

回表
    二级索引的索引树上只有该索引字段的值，如果需要获取其他值则需要去聚集索引的叶子节点查询查询，这个过程叫回表

覆盖索引
    不需要回表，直接在索引树上能得到数据叫覆盖索引

索引下推
索引下推（index condition push down ）简称ICP，在Mysql5.6的版本上推出，用于优化查询。
    比如建了两个字段的联合索引，在使用联合索引时，第一个索引是个范围或模糊，这个时候查询出来的数据很多，
    mysql底层会在回表前 根据第二个索引去匹配，如果匹配到了就会少很多数据
根据explain解析结果可以看出Extra的值为Using index condition，表示已经使用了索引下推。

索引下推在非主键索引上的优化，可以有效减少回表的次数，大大提升了查询的效率。
关闭索引下推可以使用如下命令，配置文件的修改不再讲述了，毕竟这么优秀的功能干嘛关闭呢：

##执行计划
```text
mysql> EXPLAIN sql;
*************************** 1. row ***************************
 id: 1
 select_type: SIMPLE
 table: customer
 partitions: NULL
 type: range
 possible_keys: idx_last_name,idx_full_name
 key: idx_full_name
 key_len: 274
 ref: NULL
 rows: 28
 filtered: 11.11
 Extra: Using where; Using index
1 row in set, 1 warning (0.00 sec)
```
sql调优：
    