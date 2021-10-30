package com.iris.rpc.annotion;

import com.iris.rpc.util.MyRpcRequest;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    @Value("${remote.host:localhost}")
    private String host;

    @Value("${remote.port:8080}")
    private int port;

    public RemoteInvocationHandler() {

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        MyRpcRequest rpcRequest = new MyRpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setType(method.getParameterTypes());
        rpcRequest.setArgs(args);
        Object send = rpcNetTransport.send(rpcRequest);
        // 传输数据  封装数据
        return send;
    }
}
