package com.iris.rpc;

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
            if(field.isAnnotationPresent(RemoteReference.class)){
                field.setAccessible(true);
                Object object = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[]{field.getType()}, remoteInvocationHandler);
                try {
                    field.set(bean,object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
