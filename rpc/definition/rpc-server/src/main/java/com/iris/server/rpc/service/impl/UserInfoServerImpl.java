package com.iris.server.rpc.service.impl;

import com.iris.rpc.server.IUserInfoServer;
import com.iris.server.rpc.annotion.MyRemoteServer;
import lombok.extern.slf4j.Slf4j;

@MyRemoteServer
@Slf4j
public class UserInfoServerImpl implements IUserInfoServer {

    @Override
    public Object getUserInfo(String userId) {

        return "服务段得到userId:" + userId;
    }
}
