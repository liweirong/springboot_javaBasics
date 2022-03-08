package com.iris.mq.rocketmq.service;

/**
 * @author iris
 * @date 2022/3/3
 */
public interface TransactionLogService {
    Integer get(String transactionId);
}
