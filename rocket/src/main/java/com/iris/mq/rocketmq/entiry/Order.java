package com.iris.mq.rocketmq.entiry;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author iris
 * @date 2022/3/3
 */
@Data
@Table(name = "rocket_order")
public class Order {
    private String id;
    private String orderNo;
}
