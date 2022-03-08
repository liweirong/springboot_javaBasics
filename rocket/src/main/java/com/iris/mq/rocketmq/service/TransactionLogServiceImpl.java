package com.iris.mq.rocketmq.service;

import com.iris.mq.rocketmq.entiry.TransactionLog;
import com.iris.mq.rocketmq.mapper.TransactionLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author iris
 * @date 2022/3/3
 */
@Service
@Slf4j
public class TransactionLogServiceImpl implements TransactionLogService {
    @Autowired
    private TransactionLogMapper transactionLogMapper;

    @Override
    public Integer get(String transactionId) {
        return transactionLogMapper.selectByExample(transactionId) != null ? 1 : 0;
    }
}
