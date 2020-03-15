package com.iris.mq;

import com.iris.mq.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author iris
 * @date 2019/8/21
 */
@Component
public class UserSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 传输对象需要实现Serializable接口
     */
    public void send(User user) {
//        User user = new User();
//        user.setUserName("username");
//        user.setPassWord("pwd");
        rabbitTemplate.convertAndSend("userQueue", user);
    }
}
