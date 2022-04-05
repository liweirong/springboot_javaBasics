package com.iris.service.impl;


import com.iris.entity.Order;
import com.iris.mapper.OrderMapper;
import com.iris.service.AccountService;
import com.iris.service.OrderService;
import com.iris.service.StorageService;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private AccountService accountService;
    private OrderMapper orderMapper;

    @Override
    public Order create(String userId, String commodityCode, int orderCount) {
        int orderMoney = calculate(commodityCode, orderCount);
        accountService.debit(userId, orderMoney);

        Order order = Order.builder()
                .userId(userId)
                .commodityCode(commodityCode)
                .count(orderCount)
                .money(orderMoney)
                .build();

        // INSERT INTO orders ...
        if (orderMapper.insert(order) == 1) {
            return order;
        }
        return null;
    }

    /**
     * 获得订单的钱
     *
     * @param commodityCode
     * @param orderCount
     * @return orderMoney
     */
    private int calculate(String commodityCode, int orderCount) {
        log.info("计算订单的钱为   commodityCode:{} orderCount:{}  = 1 ", commodityCode, orderCount);
        return 1;
    }
}