mysql的存储引擎有哪些？
    innodb、myisam、memory...
innodb、myisam的区别
    innodb有事务
    innodb支持崩溃恢复
    InnoDB支持表、行(默认)级锁，而MyISAM支持表级锁
    innodb底层文件有
    innoDB支持外键
    InnoDB是聚集索引，MyISAM是非聚集索引 (InnoDB使用B+Tree作为索引结构，数据文件是和（主键）索引绑在一起的,MyISAM是非聚集索引，也是使用B+Tree作为索引结构，索引和数据文件是分离的，索引保存的是数据文件的指针。主键索引和辅助索引是独立的。
    Innodb存储文件有frm、ibd，而Myisam是frm、MYD、MYI
        Innodb：frm是表定义文件，ibd是数据文件
        Myisam：frm是表定义文件，myd是数据文件，myi是索引文件
事务特性？
    ACID 原子性、一致性、隔离性、持久化（原子性，持久性，隔离性都是为了保证我们事务的一致性）

事务并发会造成什么问题？
一个事务里的两次查询，两次查询不一致造成读不一致
    脏读：读到其他事务未提交的数据，
    不可重复度：范围查询时查询到其他事务修改或删除已提交的事务
    幻读：范围查询时读到其他事务插入的数据

如何解决？数据库解决：隔离级别
隔离级别
    RU  读未提交 啥也没解决 可能出现 脏读、不可重复读、幻读
    RC  读已提交 解决了脏读
    RR  可重复读 解决了脏读、可重复读 innodb还解决了幻读
    Serial  串行化 解决了所有

innodb如何解决幻读的？
    mvcc解决读一致性
    

lbcc解决写一致性