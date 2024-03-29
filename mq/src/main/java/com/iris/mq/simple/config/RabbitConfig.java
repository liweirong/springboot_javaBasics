package com.iris.mq.simple.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    // 两个交换机
    @Bean("topicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    /**
     * 扇型交换机
     * 它会把所有发送到该交换器的消息路由到所有与该交换器绑定的队列中
     *
     * @return
     */
    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange("FANOUT_EXCHANGE");
    }

    // 三个队列
    @Bean("firstQueue")
    public Queue getFirstQueue() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl", 6000); // 设置队列过期时间
        Queue queue = new Queue("FIRST_QUEUE", false, false, true, args);
        return queue;
    }

    @Bean("secondQueue")
    public Queue getSecondQueue() {
        return new Queue("SECOND_QUEUE");
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue() {
        return new Queue("THIRD_QUEUE");
    }

    // 两个绑定
    @Bean
    public Binding bindSecond(@Qualifier("secondQueue") Queue queue, @Qualifier("topicExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#.iris.#");
    }

    @Bean
    public Binding bindThird(@Qualifier("thirdQueue") Queue queue, @Qualifier("fanoutExchange") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

}
