package com.iris.mq.rocketmq.service;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson.JSON;
import com.iris.mq.rocketmq.config.RocketMqConsumerProperties;
import com.iris.mq.rocketmq.config.transcation.TransactionProducer;
import com.iris.mq.rocketmq.entiry.Order;
import com.iris.mq.rocketmq.entiry.TransactionLog;
import com.iris.mq.rocketmq.mapper.OrderMapper;
import com.iris.mq.rocketmq.mapper.TransactionLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author iris
 * @date 2022/3/3
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private static final String topic = "lwr_topic";
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    TransactionLogMapper transactionLogMapper;
    @Autowired
    TransactionProducer producer;
    @Autowired
    RocketMqConsumerProperties rocketMqConsumerProperties;

    Snowflake snowflake = new Snowflake(1, 1);

    //执行本地事务时调用，将订单数据和事务日志写入本地数据库
    @Transactional
    @Override
    public void createOrder(Order order, String transactionId) {
        //1.创建订单
        orderMapper.insertSelective(order);
        //2.写入事务日志
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setId(transactionId);
        transactionLog.setBusiness("order");
        transactionLog.setForeignKey(order.getId());
        transactionLogMapper.insert(transactionLog);
        log.info("订单创建完成。{}", order);
    }

    //前端调用，只用于向RocketMQ发送事务消息
    @Override
    public void createOrder(Order order) throws MQClientException {
        order.setId(snowflake.nextIdStr());
        order.setOrderNo(snowflake.nextIdStr());

        producer.send(JSON.toJSONString(order), topic);
    }
}
