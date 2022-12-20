package com.iris.service;

/**
 * @author iris
 * @date 2022/4/5
 */
public interface AccountService {

    /**
     * 从用户账户中借出
     *
     * @param userId
     * @param money
     */
    void debit(String userId, int money);
}
