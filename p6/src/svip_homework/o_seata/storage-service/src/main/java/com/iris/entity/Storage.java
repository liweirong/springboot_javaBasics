package com.iris.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 */
@Data
@TableName(value = "storage_tbl")
public class Storage {
    private Integer id; // ID，数据库自增
    private String commodityCode;
    private Integer count;
}
