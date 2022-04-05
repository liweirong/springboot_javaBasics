package com.iris.service.impl;

import com.iris.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author iris
 * @date 2022/4/5
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public void debit(String userId, int money) {
        log.info("账户扣除userId:{} , money:{} ", userId, money);
    }
}
