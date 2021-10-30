package com.iris.rpc.annotion;



import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class ReferenceInvokeProxy implements BeanPostProcessor {

    @Autowired
    private RemoteInvocationHandler remoteInvocationHandler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            // 如果有自定义的注解
            if (field.isAnnotationPresent(MyRemoteServer.class)) {
                field.setAccessible(true);
                // 返回代理类
                Object object = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[]{field.getType()}, remoteInvocationHandler);
                try {
                    field.set(bean, object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
