package com.iris.rpc;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class BeanMethod {

    private Object bean;
    private Method method;

}
