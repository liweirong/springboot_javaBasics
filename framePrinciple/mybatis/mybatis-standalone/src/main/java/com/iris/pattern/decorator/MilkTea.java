package com.iris.pattern.decorator;

/**
 * @Author: iris
 * @Date: 2019/3/11 00:34
 * @Description:
 */
public class MilkTea extends Drink {
    public MilkTea() {
        desc = "奶茶";
    }

    @Override
    public double cost() {
        return 22;
    }
}
