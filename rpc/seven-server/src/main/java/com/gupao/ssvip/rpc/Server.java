package com.gupao.ssvip.rpc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gupao.ssvip.rpc")
public class Server {

    public static void main(String[] args) {

//        IOrderService iOrderService = new OrderServiceImpl();
//        RpcProxyServer rpcProxyServer = new RpcProxyServer();
//        rpcProxyServer.publisher(iOrderService,8080);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Server.class);

    }
}
