package com.iris.pattern.decorator;

/**
 * @Author: iris
 * @Date: 2019/3/11 00:33
 * @Description:
 *
 * 饮料——抽象类
 * https://blog.csdn.net/zaoan_2010/article/details/83308891
 */
public abstract class Drink {
    // 饮料名称
    protected String desc;

    public String getDesc() {
        return desc;
    }

    // 饮料价格
    public abstract double cost();
}
