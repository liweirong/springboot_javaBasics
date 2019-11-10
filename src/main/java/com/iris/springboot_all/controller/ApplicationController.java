package com.iris.springboot_all.controller;

import com.iris.springboot_all.model.MyInfo;
import com.iris.springboot_all.rabbitMq.simple.provider.MyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iris
 * @date 2019/11/10
 */
@RestController
@EnableConfigurationProperties({MyInfo.class})
public class ApplicationController {
    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private String age;


    @RequestMapping("/myInfo")
    public String myInfo(){
        return name+":"+age;
    }
    @Autowired
    private MyInfo myInfo;
    @RequestMapping("/myBeanInfo")
    public String myBeanInfo(){
        return myInfo.toString();
    }
}
