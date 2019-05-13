package com.iris.springboot_all.work.Pattern.singleton;

import com.iris.springboot_all.work.Pattern.singleton.register.EnumSingleton;

import java.lang.reflect.Constructor;

/**
 * Created by iris.
 */
public class EnumSingletonTest {
//    public static void main(String[] args) {
//        try {
//            EnumSingleton instance1 = null;
//
//            EnumSingleton instance2 = EnumSingleton.getInstance();
//            instance2.setData(new Object());
//
//            FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(instance2);
//            oos.flush();
//            oos.close();
//
//            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            instance1 = (EnumSingleton) ois.readObject();
//            ois.close();
//
//            System.out.println(instance1.getData());
//            System.out.println(instance2.getData());
//            System.out.println(instance1.getData() == instance2.getData());
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) {
        try {
            Class clazz = EnumSingleton.class;
            Constructor c = clazz.getDeclaredConstructor(String.class, int.class);
            c.setAccessible(true);
            //java.lang.IllegalArgumentException: Cannot reflectively create enum objects
            //	at java.lang.reflect.Constructor.newInstance(Constructor.java:417)
            EnumSingleton enumSingleton = (EnumSingleton) c.newInstance("iris", 666);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}