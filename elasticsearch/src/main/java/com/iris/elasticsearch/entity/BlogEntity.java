package com.iris.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 体系结构：
 * 	Es			    |		mysql
 * 	索引(index)		|		数据库(databases)
 * 	类型(type)		|		表(table)
 * 	文档(document)	|		行(roW)
 * @author iris
 * @date 2020/4/11
 */
@Data
@Document(indexName = "blog",type = "doc")
public class BlogEntity {
    @Id
    private String blogIndex;


}
