package com.iris.springboot_all.rabbitMq;

import com.iris.springboot_all.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author iris
 * @date 2019/8/20
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }


}
