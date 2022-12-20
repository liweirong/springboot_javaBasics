package com.iris.service;


import com.iris.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     *
     * @param userId
     * @param commodityCode
     * @param orderCount
     * @return
     */
    Order create(String userId, String commodityCode, int orderCount);
}
