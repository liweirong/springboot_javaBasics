package com.iris.springboot_all.work.Pattern.Factory.A_SimpleFactoryPattern;

/**
 * @author iris
 * @date 2019/5/5
 */
public class SimpleFactory {

    public IMan create(Class clazz) {
        IMan man = null;
        try {
            if (null != clazz) {
                man = (IMan) clazz.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return man;
    }
}
