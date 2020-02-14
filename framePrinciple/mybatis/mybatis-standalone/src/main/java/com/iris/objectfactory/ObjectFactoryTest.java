package com.iris.objectfactory;

import com.iris.domain.Blog;

/**
 * @Author: iris
 * @Date: 2019/3/25 19:41
 * @Description:
 */
public class ObjectFactoryTest {
    public static void main(String[] args) {
        GPObjectFactory factory = new GPObjectFactory();
        Blog myBlog = (Blog) factory.create(Blog.class);
        System.out.println(myBlog);
    }
}
