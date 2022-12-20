package com.iris.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @author iris
 * @date 2022/4/5
 */
@Builder
@Data
@TableName(value = "order_tbl")
public class Order {
    /**
     * 雪花算法
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id; // ID，数据库自增
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;
}
