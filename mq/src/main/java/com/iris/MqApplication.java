package com.iris;

import com.iris.mq.UserSender;
import com.iris.mq.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private UserSender userSender;

    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg") String msg) {
        System.out.println(msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
        userSender.send(new User(UUID.randomUUID().toString(), "love", "password"));
        this.rabbitTemplate.convertAndSend("iris.ori.use", msg);
        return "msg";
    }

    @GetMapping("/sendDelay/{msg}")
    public void sendDelay(@PathVariable("msg") String msg) {
        MessageProperties messageProperties = new MessageProperties();
        // 延迟的间隔时间，目标时刻减去当前时刻
        messageProperties.setHeader("x-delay", 1000);
        Message message = new Message(msg.getBytes(), messageProperties);
        // 不能在本地测试，必须发送消息到安装了插件的 Linux 服务端
        rabbitTemplate.send("GP_DELAY_EXCHANGE", "#", message);
    }


}
