package com.iris.domain;

import java.io.Serializable;

/**
 * @Author: iris
 * @Date: 2019/4/13 19:34
 * @Description:
 */
public class Author implements Serializable {
    Integer authorId; // 作者ID
    String authorName; // 作者名称

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
