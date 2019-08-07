package com.iris.springboot_all.Interview;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: iris
 * @date: 2019/2/6
 */
@Getter
@Setter
public class DataWrap<T> {

    /**
     * 数据类型
     */
    private String type;

    /**
     * 解析数据的配置文件
     */
    private String config;

    /**
     * 索引名称
     */
    private String indexName;

    /**
     * 索引所在的目录
     */
    private String indexDir;

    /**
     * 当前数据是不是需要恢复合并的数据，
     * 是为true，否则为false
     */
    private boolean recover;

    /**
     * 存储需要消费的数据
     */
    private List<T> data = new ArrayList<>();

    public DataWrap() {
    }

    public DataWrap(String indexName, String indexDir) {
        this.indexName = indexName;
        this.indexDir = indexDir;
    }
}
