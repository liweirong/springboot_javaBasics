package com.iris.springboot_all.work.Pattern.singleton.distorySinglen;

import com.iris.springboot_all.work.Pattern.singleton.lazy.LazyInnerClassSingleton;

import java.lang.reflect.Constructor;

/**
 *  在构造方法中加入判断防止单例破坏
 *  if(LazyHolder.LAZY != null){
 *             throw new RuntimeException("不允许创建多个实例");
 *  }
 * @author iris
 * @date 2019/5/28
 */
public class reflect {
    public static void main(String[] args) {
        try {
            //很无聊的情况下，进行破坏
            Class<?> clazz = LazyInnerClassSingleton.class;
            //通过反射拿到私有的构造方法
            Constructor c = clazz.getDeclaredConstructor(null);
            //强制访问，强吻，不愿意也要吻
            c.setAccessible(true);
            //暴力初始化
            Object o1 = c.newInstance();

            //调用了两次构造方法，相当于 new 了两次
            //犯了原则性问题，
            Object o2 = c.newInstance();
            System.out.println(o1 == o2);
            // Object o2 = c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
