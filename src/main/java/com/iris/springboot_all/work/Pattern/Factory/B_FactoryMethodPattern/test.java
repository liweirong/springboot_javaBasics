package com.iris.springboot_all.work.Pattern.Factory.B_FactoryMethodPattern;

import com.iris.springboot_all.work.Pattern.Factory.B_FactoryMethodPattern.factory.IManFactory;
import com.iris.springboot_all.work.Pattern.Factory.B_FactoryMethodPattern.factory.PopulaceFactory;
import com.iris.springboot_all.work.Pattern.Factory.B_FactoryMethodPattern.factory.SoldierFactory;

/**
 * @author iris
 * @date 2019/5/5
 */
public class test {
    public static void main(String[] args) {
        IManFactory soldierFactory = new SoldierFactory();
        IMan soldier = soldierFactory.create();
        soldier.fight();

        IManFactory populaceFactory = new PopulaceFactory();
        IMan populace = populaceFactory.create();
        populace.fight();
    }
}
