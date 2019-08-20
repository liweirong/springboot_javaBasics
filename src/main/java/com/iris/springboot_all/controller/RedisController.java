package com.iris.springboot_all.controller;

import com.iris.springboot_all.model.User;
import com.iris.springboot_all.server.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * redis命令 - http://redisdoc.com/
 * @author iris
 * @date 2019/8/20
 */
@Controller
public class RedisController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisService redisService;

/**
 * 实现栈 后进先出：lpush lpop
 * 实现队列 先进先出 lpush rpop
 * 消息队列 lpush brpop
 */
    /**
     * key的设计：命名清晰且短
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable(name = "userId") String userId) {
        // 保存字符串
//        User user  = redisService.getUserByUserId(userId);
        String s = stringRedisTemplate.opsForValue().get(userId);
        return s;
    }

    @RequestMapping(value = "/user/{key}/{value}", method = RequestMethod.GET)
    @ResponseBody
    public void setValue(@PathVariable(name = "key") String key, @PathVariable(name = "value") String value) {
        // 保存字符串
        stringRedisTemplate.opsForValue().set(key, value);
    }
}
