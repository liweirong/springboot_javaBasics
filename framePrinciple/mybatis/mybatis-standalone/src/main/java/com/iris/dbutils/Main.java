package com.iris.dbutils;

import com.iris.dbutils.dao.BlogDao;

public class Main {
    public static void main(String[] args) throws Exception {
        HikariUtil.init();
        BlogDao.selectBlog(1);
        BlogDao.selectList();
    }
}
