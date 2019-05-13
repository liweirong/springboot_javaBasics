package com.iris.springboot_all.work.Pattern.singleton;

import com.iris.springboot_all.work.Pattern.singleton.register.ContainerSingleton;

/**
 * Created by iris.
 */
public class ContainerSingletonTest {
    public static void main(String[] args) {


        try {
            long start = System.currentTimeMillis();
            ConcurrentExecutor.execute(new ConcurrentExecutor.RunHandler() {
                public void handler() {
                    Object obj = ContainerSingleton.getInstance("com.iris.springboot_all.work.Pattern.singleton.test.Pojo");
                    System.out.println(System.currentTimeMillis() + ": " + obj);
                }
            }, 10,6);
            long end = System.currentTimeMillis();
            System.out.println("总耗时：" + (end - start) + " ms.");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
