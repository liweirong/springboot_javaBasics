package com.iris.rpc;

import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    @Value("${rpc.remote.host}")
    private String host;

    @Value("${rpc.remote.port}")
    private int port;

    public RemoteInvocationHandler() {

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host,port);

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setType(method.getParameterTypes());
        rpcRequest.setArgs(args);
        Object send = rpcNetTransport.send(rpcRequest);
        // 传输数据  封装数据
        return send;
    }
}
