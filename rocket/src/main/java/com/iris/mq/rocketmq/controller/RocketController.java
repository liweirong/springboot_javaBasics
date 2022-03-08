package com.iris.mq.rocketmq.controller;

import com.iris.mq.rocketmq.entiry.Order;
import com.iris.mq.rocketmq.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iris
 * @date 2021/8/26
 */
@RestController
@Slf4j
@Api(tags = "搜索接口")
public class RocketController {

    /**
     * 使用RocketMq的生产者
     */
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    @Autowired
    private OrderService orderService;

    @ApiOperation("生产消息")
    @PostMapping("sendMessage")
    public void createMessage(@RequestParam("message") String message) {
        log.info("开始发送消息：{}", message);
        Message sendMsg = new Message("lwr_topic_test", "*", message.getBytes());
        //默认3秒超时
        SendResult sendResult = null;
        try {
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (MQClientException e) {
            log.error("MQClientException {}", e);
        } catch (RemotingException e) {
            log.error("RemotingException {}", e);
        } catch (MQBrokerException e) {
            log.error("MQBrokerException {}", e);
        } catch (InterruptedException e) {
            log.error("InterruptedException {}", e);
        }
        if (sendResult != null) {
            log.info("消息 [{}] 发送响应信息：{}", sendMsg, sendResult.toString());
        }
    }

    @ApiOperation("事务回退")
    @PostMapping("createOrder")
    public void createOrder(@RequestBody Order order) throws MQClientException {
        orderService.createOrder(order);
    }
}
