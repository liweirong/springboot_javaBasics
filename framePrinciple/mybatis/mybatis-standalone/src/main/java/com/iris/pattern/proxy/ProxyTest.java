package com.iris.pattern.proxy;

/**
 * @Author: iris
 * @Date: 2019/3/7 15:01
 * @Description:
 */
public class ProxyTest {
    public static void main(String[] args) {
        /**
         * 中介帮助房东出租房子的过程
         */
        RentServiceProxy HelloHandler = new RentServiceProxy();
        RentService proxy = (RentService) HelloHandler.getProxy(new RentServiceImpl());
        proxy.rent("海滨一号");
    }
}