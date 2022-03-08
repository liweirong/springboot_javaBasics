package com.iris.mq.rocketmq.config.transcation;

import com.alibaba.fastjson.JSONObject;
import com.iris.mq.rocketmq.entiry.Order;
import com.iris.mq.rocketmq.service.OrderService;
import com.iris.mq.rocketmq.service.TransactionLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author iris
 * @date 2022/3/3
 */
@Component
@Slf4j
public class OrderTransactionListener implements TransactionListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TransactionLogService transactionLogService;


    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        log.info("开始执行本地事务....");
        LocalTransactionState state;
        try{
            String body = new String(message.getBody());
            Order order = JSONObject.parseObject(body, Order.class);
            orderService.createOrder(order,message.getTransactionId());
            state = LocalTransactionState.COMMIT_MESSAGE;
            log.info("本地事务已提交。{}",message.getTransactionId());
        }catch (Exception e){
            log.info("执行本地事务失败。{}",e);
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        log.info("开始回查本地事务状态。{}",messageExt.getTransactionId());
        LocalTransactionState state;
        String transactionId = messageExt.getTransactionId();
        if (transactionLogService.get(transactionId)>0){
            state = LocalTransactionState.COMMIT_MESSAGE;
        }else {
            state = LocalTransactionState.UNKNOW;
        }
        log.info("结束本地事务状态查询：{}",state);
        return state;
    }
}
