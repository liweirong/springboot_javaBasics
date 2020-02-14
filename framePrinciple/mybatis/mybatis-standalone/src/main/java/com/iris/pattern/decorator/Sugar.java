package com.iris.pattern.decorator;

/**
 * @Author: iris
 * @Date: 2019/3/11 00:36
 * @Description:
 *
 * 加糖1元
 */
public class Sugar extends Condiment {
    private Drink drink;

    public Sugar(Drink drink) {
        this.drink = drink;
    }

    public String getDesc() {
        return drink.getDesc() + "+糖";
    }

    public double cost() {
        return 1 + drink.cost();
    }
}
