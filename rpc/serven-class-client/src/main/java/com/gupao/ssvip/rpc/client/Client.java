package com.gupao.ssvip.rpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Client {

    public static void main(String[] args) {

//        RpcClientProxy rpcClientProxy = new RpcClientProxy();
//        IOrderService iOrderService = rpcClientProxy.clientProxy(IOrderService.class, "localhost", 8080);
//        System.out.println(iOrderService.queryOrderList());
        SpringApplication.run(Client.class,args);

    }
}
