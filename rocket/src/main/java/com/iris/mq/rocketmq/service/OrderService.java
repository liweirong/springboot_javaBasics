package com.iris.mq.rocketmq.service;

import com.iris.mq.rocketmq.entiry.Order;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author iris
 * @date 2022/3/3
 */
public interface OrderService {
    void createOrder(Order order, String transactionId);

    void createOrder(Order order) throws MQClientException;
}
