package com.iris.springboot_all.work.Pattern.Factory.A_SimpleFactoryPattern;

/**
 * @author iris
 * @date 2019/5/5
 */
public class test {
    public static void main(String[] args) {
        SimpleFactory factory= new SimpleFactory();
        IMan soldier = factory.create(Soldier.class);
        soldier.fight();
        IMan populace = factory.create(Populace.class);
        populace.fight();

        /**
         *
         * 优点：只需呀传入正确参数就可得到实例
         * 缺点：工厂类职责过重，新增产品时违背开闭原则，不利于扩展过于复杂的产品结构
         *
         */
    }
}
