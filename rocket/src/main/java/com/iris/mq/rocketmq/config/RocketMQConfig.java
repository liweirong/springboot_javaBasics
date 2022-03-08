package com.iris.mq.rocketmq.config;


import com.iris.mq.rocketmq.config.transcation.TransactionConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
/**
 * @author iris
 * @date 2021/8/26
 */
@Configuration
@Slf4j
public class RocketMQConfig {

    @Resource
    private RocketMqProducerProperties rocketMqProducerProperties;

    @Resource
    private RocketMqConsumerProperties rocketMqConsumerProperties;

    @Resource
    private TransactionConsumer transactionConsumer;

    /***
     * 生产者
     * @return
     */
    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        if (StringUtils.isEmpty(rocketMqProducerProperties.getGroupName())) {
            throw new RuntimeException("rocketMq producer groupName is blank");
        }
        if (StringUtils.isEmpty(rocketMqProducerProperties.getNamesrvAddr())) {
            throw new RuntimeException("rocketMq producer nameServerAddr is blank");
        }
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(rocketMqProducerProperties.getGroupName());
        producer.setNamesrvAddr(rocketMqProducerProperties.getNamesrvAddr());
        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        if (rocketMqProducerProperties.getMaxMessageSize() != null) {
            producer.setMaxMessageSize(rocketMqProducerProperties.getMaxMessageSize());
        }
        if (rocketMqProducerProperties.getSendMsgTimeout() != null) {
            producer.setSendMsgTimeout(rocketMqProducerProperties.getSendMsgTimeout());
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if (rocketMqProducerProperties.getRetryTimesWhenSendFailed() != null) {
            producer.setRetryTimesWhenSendFailed(rocketMqProducerProperties.getRetryTimesWhenSendFailed());
        }

        try {
            producer.start();

            log.info(String.format("producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , rocketMqProducerProperties.getGroupName(), rocketMqProducerProperties.getNamesrvAddr()));
        } catch (MQClientException e) {
            log.error(String.format("producer is error {}"
                    , e.getMessage(), e));
            throw new RuntimeException(e);
        }
        return producer;
    }

    /***
     * 消费者
     * @return
     */
    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer() {
        if (StringUtils.isEmpty(rocketMqConsumerProperties.getGroupName())) {
            throw new RuntimeException("rocketMq consumer groupName is null !");
        }
        if (StringUtils.isEmpty(rocketMqConsumerProperties.getNamesrvAddr())) {
            throw new RuntimeException("rocketMq consumer namesrvAddr is null !");
        }
        if (StringUtils.isEmpty(rocketMqConsumerProperties.getTopics())) {
            throw new RuntimeException("rocketMq consumer topics is null !");
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMqConsumerProperties.getGroupName());
        consumer.setNamesrvAddr(rocketMqConsumerProperties.getNamesrvAddr());
        consumer.setConsumeThreadMin(rocketMqConsumerProperties.getConsumeThreadMin());
        consumer.setConsumeThreadMax(rocketMqConsumerProperties.getConsumeThreadMax());
        //消息消费处理类
        consumer.registerMessageListener(transactionConsumer);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置消费模型，集群还是广播，默认为集群
         */
        //consumer.setMessageModel(MessageModel.CLUSTERING);

        /**
         * 设置一次消费消息的条数，默认为1条
         */
        consumer.setConsumeMessageBatchMaxSize(rocketMqConsumerProperties.getConsumeMessageBatchMaxSize());
        try {
            /**
             * 设置该消费者订阅的主题和tag，如果是订阅该主题下的所有tag，则tag使用*；如果需要指定订阅该主题下的某些tag，则使用||分割，例如tag1||tag2||tag3
             */
            String[] topicTagsArr = rocketMqConsumerProperties.getTopics().split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0], topicTag[1]);
            }
            consumer.start();
            log.info("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}", rocketMqConsumerProperties.getGroupName(),
                    rocketMqConsumerProperties.getTopics(), rocketMqConsumerProperties.getNamesrvAddr());
        } catch (MQClientException e) {
            log.error("consumer is start !!! groupName:{},topics:{},namesrvAddr:{}" , rocketMqConsumerProperties.getGroupName(),
                    rocketMqConsumerProperties.getTopics(), rocketMqConsumerProperties.getNamesrvAddr(), e);
            throw new RuntimeException(e);
        }
        return consumer;
    }
}