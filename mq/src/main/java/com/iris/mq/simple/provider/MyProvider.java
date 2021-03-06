package com.iris.mq.simple.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyProvider {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send() {
        // 发送4条消息

        amqpTemplate.convertAndSend("", "FIRST_QUEUE", "-------- a direct msg");

        amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "shanghai.iris.teacher", "-------- a topic msg : shanghai.iris.teacher");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE", "changsha.iris.student", "-------- a topic msg : changsha.iris.student");

        amqpTemplate.convertAndSend("FANOUT_EXCHANGE", "", "-------- a fanout msg");

    }

}
