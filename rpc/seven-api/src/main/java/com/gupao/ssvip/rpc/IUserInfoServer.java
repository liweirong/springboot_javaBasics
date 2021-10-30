package com.gupao.ssvip.rpc;


/**
 * @author iris
 * @date 2021/10/30
 */
public interface IUserInfoServer {
    /**
     * 通过用户id获取详情
     *
     * @param userId userId
     * @return 详情
     */
    Object getUserInfo(String userId);
}
