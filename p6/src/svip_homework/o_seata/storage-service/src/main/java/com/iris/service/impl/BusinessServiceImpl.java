package com.iris.service.impl;

import com.iris.service.BusinessService;
import com.iris.service.OrderService;
import com.iris.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author iris
 * @date 2022/4/5
 */
@AllArgsConstructor
@Service
public class BusinessServiceImpl implements BusinessService {

    private StorageService storageService;
    private OrderService orderService;

    @Override
    @GlobalTransactional
    public void purchase(String userId, String commodityCode, int orderCount) {
        storageService.deduct(commodityCode, orderCount);
        int i = 1 / Integer.valueOf(commodityCode);
        orderService.create(userId, commodityCode, orderCount);
    }
}
