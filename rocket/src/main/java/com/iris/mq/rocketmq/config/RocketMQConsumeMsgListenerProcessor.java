package com.iris.mq.rocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author iris
 * @date 2021/8/26
 */
@Component
@Slf4j
public class RocketMQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            log.info("接受到的消息为空，不处理，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);
        log.info("接受到的消息为：" + messageExt.toString());
        if ("你的Topic".equals(messageExt.getTopic())) {
            if ("你的Tag".equals(messageExt.getTags())) {
                //TODO 判断该消息是否重复消费（RocketMQ不保证消息不重复，如果你的业务需要保证严格的不重复消息，需要你自己在业务端去重）
                //TODO 获取该消息重试次数
                int reconsume = messageExt.getReconsumeTimes();
                String topic = messageExt.getTopic();
                String tags = messageExt.getTags();
                String body = messageExt.toString();
                log.info("MQ消息topic={}, tags={}, 消息内容={}", topic, tags, body);
                if (reconsume == 3) {//消息已经重试了3次，如果不需要再次消费，则返回成功
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                //TODO 处理对应的业务逻辑
            }
        }
        // 如果没有return success ，consumer会重新消费该消息，直到return success
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}