package com.iris.elasticsearch.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author iris
 * @date 2020/4/11
 */
@Api(tags = "搜索接口")
@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    @ResponseBody
    @ApiOperation("测试")
    public String test(){
        return "hello"+port;
    }

    @GetMapping("/es/{indexName}")
    @ResponseBody
    @ApiOperation("创建一个索引")
    public String getESIndex(@PathVariable String indexName){
        boolean test = elasticsearchTemplate.createIndex(indexName);
        return test?"success":"false";
    }

    @GetMapping("/deles/{indexName}")
    @ResponseBody
    @ApiOperation("删除一个索引")
    public String delESIndex(@PathVariable String indexName){
        boolean test = elasticsearchTemplate.deleteIndex(indexName);
        return test?"success":"false";
    }
}
