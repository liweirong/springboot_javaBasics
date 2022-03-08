package com.iris.mq.rocketmq.entiry;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author iris
 * @date 2022/3/3
 * 积分
 */
@Data
@Table(name = "rocket_t_points")
public class Points {
    private String id;
    private String userId;
    private String orderNo;
    private Integer points;
    private String remarks;
}
