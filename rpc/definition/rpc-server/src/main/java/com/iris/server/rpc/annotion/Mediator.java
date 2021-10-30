package com.iris.server.rpc.annotion;

import com.iris.rcp.util.MyRpcRequest;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class Mediator {

    public static Map<String, BeanMethod> map = new ConcurrentHashMap<>();

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
        log.info("获得一个单例的代理bean容器");
        return instance;
    }

    /**
     * 通过key 名字+方法，从map中取出BeanMethod,可以得到bean,method，通过反射调用
     *
     * @param rpcRequest rpcRequest
     * @return 通过反射调用
     */
    public Object processor(@NotNull MyRpcRequest rpcRequest) {
        String key = rpcRequest.getClassName() + "." + rpcRequest.getMethodName();
        BeanMethod beanMethod = map.get(key);

        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();
        try {
            log.info("key:{}，从map中取出BeanMethod:{} 通过反射调用", key, beanMethod);
            return method.invoke(bean, rpcRequest.getArgs());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
