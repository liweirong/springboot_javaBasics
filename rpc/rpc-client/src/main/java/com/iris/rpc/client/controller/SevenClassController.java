package com.iris.rpc.client.controller;

import com.iris.rpc.IUserInfoServer;
import com.iris.rpc.RemoteReference;
import com.iris.rpc.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SevenClassController {

    @RemoteReference
    private IOrderService iOrderService;
    @RemoteReference
    private IUserInfoServer userInfoServer;

    @GetMapping("/test/order")
    public String test() {
        return iOrderService.queryOrderList();
    }

    @GetMapping("/user")
    public Object userInfo() {
        return userInfoServer.getUserInfo("1");
    }
}
