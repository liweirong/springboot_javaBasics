package com.iris.springboot_all.rabbitMq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiverTest {

    @Autowired
    private Receiver receiver;
    @Test
    public void process() {
        System.out.println("开始读取消息");
        receiver.process("1111");
        System.out.println("结束");
    }
}