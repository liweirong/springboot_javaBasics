package com.iris.client.rpc.controller;

import com.iris.rcp.annotion.MyRemoteServer;
import com.iris.rcp.server.IUserInfoServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iris
 * @date 2021/10/30
 */
@RestController
public class ClientController {

    @MyRemoteServer
    private IUserInfoServer userInfoServer;

    @GetMapping("test/{userId}")
    private Object getTest(@PathVariable("userId") String userId) {
        return userInfoServer.getUserInfo(userId);
    }
}
