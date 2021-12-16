package com.iris.springcloud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iris
 * @date 2021/12/15
 */
@RestController
@RefreshScope
@Api("nacos动态刷新")
public class ConfigController {

    @Value("${name:defalut}")
    private String name;

    @Autowired
    private Environment environment;

    @ApiOperation("get")
    @GetMapping("/get/{key}")
    @ResponseBody
    public String get(@PathVariable("key") String key) {
        String property = environment.getProperty(key);
        return "name:" + name + "|" + key + ":" + property;
    }
}
