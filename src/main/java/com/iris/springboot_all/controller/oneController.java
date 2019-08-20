package com.iris.springboot_all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iris
 * @date 2019/4/23
 */
@Controller
public class oneController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    private Map hello() {
        Map map = new HashMap<String, Object>();
        map.put("hello", "word");
        return map;
    }


}
