package com.iris.domain;

import java.io.Serializable;

/**
 * @Author: iris
 * @Date: 2019/1/16 13:52
 * @Description:
 */
public class Blog implements Serializable{
    private static final long serialVersionUID = 637370320359130028L;
    Integer bid; // 文章ID
    String name; // 文章标题
    Integer authorId; // 文章作者ID

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
