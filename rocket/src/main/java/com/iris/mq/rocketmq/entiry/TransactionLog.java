package com.iris.mq.rocketmq.entiry;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author iris
 * @date 2022/3/3
 */
@Data
@Table(name = "rocket_transaction_log")
public class TransactionLog {
    private String id;
    private String business;
    private String foreignKey;
}
