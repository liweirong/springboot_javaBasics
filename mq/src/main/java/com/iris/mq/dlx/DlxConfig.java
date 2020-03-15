package com.iris.mq.dlx;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iris
 * @date 2020/3/15
 */
@Configuration
public class DlxConfig {
    @Bean("oriUseExchange")
    public DirectExchange exchange() {
        return new DirectExchange("GP_ORI_USE_EXCHANGE", true, false, new HashMap<>());
    }

    @Bean("oriUseQueue")
    public Queue queue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000); // 10 秒钟后成为死信
        map.put("x-dead-letter-exchange", "GP_DEAD_LETTER_EXCHANGE"); // 队列中的消息变成死信后，进入死信
        //交换机
        return new Queue("GP_ORI_USE_QUEUE", true, false, false, map);
    }

    @Bean
    public Binding binding(@Qualifier("oriUseQueue") Queue queue, @Qualifier("oriUseExchange") DirectExchange
            exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("iris.ori.use");
    }

    /**
     * 声 明 死 信 交 换 机 （ GP_DEAD_LETTER_EXCHANGE ） 、 死 信 队 列
     * （GP_DEAD_LETTER_QUEUE），相互绑定
     *
     */
    @Bean("deadLetterExchange")
    public TopicExchange deadLetterExchange() {
        return new TopicExchange("GP_DEAD_LETTER_EXCHANGE", true, false, new HashMap<>());
    }

    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        return new Queue("GP_DEAD_LETTER_QUEUE", true, false, false, new HashMap<>());
    }

    @Bean
    public Binding bindingDead(@Qualifier("deadLetterQueue") Queue queue, @Qualifier("deadLetterExchange")
            TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#"); // 无条件路由
    }
}
