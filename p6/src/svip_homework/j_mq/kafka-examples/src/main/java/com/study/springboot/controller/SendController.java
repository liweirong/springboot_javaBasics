package com.study.springboot.controller;

import com.study.springboot.producer.Message;
import com.study.springboot.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class SendController {

    @Autowired
    private Producer producer;

    @RequestMapping(value = "send")
    public String send() {
        producer.sendMessage(new Message());
        return "{\"code\":0}";
    }
}
