package com.iris.mq.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author iris
 * @date 2021/8/26
 */
@Component
@ConfigurationProperties(prefix = "rocketmq.producer")
@Data
public class RocketMqProducerProperties {
    private String isOnOff;
    private String groupName;
    private String namesrvAddr;
    private Integer maxMessageSize;
    private Integer sendMsgTimeout;
    private Integer retryTimesWhenSendFailed;
}