package com.iris.springboot_all.work.Pattern.singleton;

import com.iris.springboot_all.work.Pattern.singleton.lazy.LazySimpleSingleton;

/**
 * Created by iris.
 */
public class ExectorThread implements Runnable{
    @Override
    public void run() {
        LazySimpleSingleton singleton = LazySimpleSingleton.getInstance();
//        ThreadLocalSingleton singleton = ThreadLocalSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ":" + singleton);
    }
}
