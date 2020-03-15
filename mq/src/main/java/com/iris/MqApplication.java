package com.iris;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MqApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg") String msg){
        System.out.println(msg);
        this.rabbitTemplate.convertAndSend("hello", msg);
        this.rabbitTemplate.convertAndSend("iris.ori.use", msg);
        return "msg";
    }


}
