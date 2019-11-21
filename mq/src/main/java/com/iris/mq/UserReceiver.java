package com.iris.mq;

import com.iris.mq.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author iris
 * @date 2019/8/21
 */
@RabbitListener(queues="userQueue")    //监听器监听指定的Queue
public class UserReceiver {


    public void process1(User user) {    //用User作为参数
        System.out.println("Receive1:"+user);
    }
}
