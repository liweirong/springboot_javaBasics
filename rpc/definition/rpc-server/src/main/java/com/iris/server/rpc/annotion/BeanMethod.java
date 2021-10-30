package com.iris.server.rpc.annotion;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class BeanMethod {
    private Object bean;

    private Method method;
}
