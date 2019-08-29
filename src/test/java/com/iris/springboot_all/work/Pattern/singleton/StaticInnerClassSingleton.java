package com.iris.springboot_all.work.Pattern.singleton;

/**
 * @author iris
 * @date 2019/8/29
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {
    }

    private static class SingleTonHoler {
        private static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return SingleTonHoler.INSTANCE;
    }

}
