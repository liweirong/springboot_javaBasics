package com.iris.server.rpc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author iris
 * @date 2021/10/30
 */
@Configuration
@ComponentScan("com.iris.server.rpc")
public class RpcServerApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RpcServerApplication.class);
    }
}
