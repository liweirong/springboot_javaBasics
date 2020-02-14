package com.iris.pattern.decorator;

/**
 * @Author: iris
 * @Date: 2019/3/11 00:34
 * @Description:
 */
public class LemonTea extends Drink{
    public LemonTea() {
        desc = "柠檬茶";
    }

    public double cost() {
        return 10;
    }
}
