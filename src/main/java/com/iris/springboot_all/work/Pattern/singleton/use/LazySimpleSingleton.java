package com.iris.springboot_all.work.Pattern.singleton.use;

/**
 * 多线程并发会出现不同的实例，一前一后才是正确的
 *
 * @author iris
 * @date 2019/5/6
 */
public class LazySimpleSingleton {
    private volatile static LazySimpleSingleton lazy = null;

    private void lazySimpleSingleton() {
    }

    /**
     * 方法上加锁会导致类的死锁
     * jdk1.6之后对synchronized性能优化不少
     * synchronized用于方法上会对整个类进行死锁，所以最好的是把锁放类里面
     *
     * @return LazySimpleSingleton 单例对象
     */
    public static LazySimpleSingleton getInstance() {
        if (lazy == null) {
            synchronized (LazySimpleSingleton.class) {
                if (lazy == null) {
                    lazy = new LazySimpleSingleton();
                }
            }
        }
        return lazy;
    }


}
