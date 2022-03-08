package com.iris.mq.rocketmq.config;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iris.mq.rocketmq.entiry.Order;
import com.iris.mq.rocketmq.entiry.Points;
import com.iris.mq.rocketmq.mapper.PointsMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author iris
 * @date 2021/8/26
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "lwr_topic", consumerGroup = "order_trans_group")
public class RocketMQConsumeMsgListenerProcessor implements RocketMQListener<Order> {

    @Autowired
    private PointsMapper pointsMapper;

    @Override
    public void onMessage(Order order) {
        if (order == null) {
            log.info("接受到的消息为空，不处理，直接返回成功");
            return;
        }
        log.info("接受到的消息为：{}", order);
        Points points = new Points();
        points.setId(UUID.randomUUID().toString());
        points.setOrderNo(order.getOrderNo());
        points.setPoints(1);
        pointsMapper.insert(points);
        log.info("更新积分:{}", points);
        //TODO 判断该消息是否重复消费（RocketMQ不保证消息不重复，如果你的业务需要保证严格的不重复消息，需要你自己在业务端去重）
        //TODO 获取该消息重试次数
        //TODO 处理对应的业务逻辑
    }
}