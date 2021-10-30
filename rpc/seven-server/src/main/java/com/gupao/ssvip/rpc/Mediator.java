package com.gupao.ssvip.rpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Mediator {

    public static Map<String, BeanMethod> map = new ConcurrentHashMap<String, BeanMethod>();

    private volatile static Mediator instance;

    private Mediator() {

    }

    public static Mediator getInstance() {
        if (instance == null) {
            synchronized (Mediator.class) {
                if (instance == null) {
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    /**
     * 通过key 名字+方法，从map中取出BeanMethod,可以得到bean,method，通过反射调用
     *
     * @param rpcRequest
     * @return
     */
    public Object processor(RpcRequest rpcRequest) {
        String key = rpcRequest.getClassName() + "." + rpcRequest.getMethodName();
        BeanMethod beanMethod = map.get(key);

        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();

        try {
            return method.invoke(bean,rpcRequest.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
