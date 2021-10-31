package com.iris.rpc;

import java.lang.reflect.Proxy;

public class RpcClientProxy{

    public <T> T clientProxy(final Class<T> interfaceCls){
      return   (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class<?>[]{interfaceCls}
        ,new RemoteInvocationHandler());
    }

}
