package com.iris.service;

/**
 * @author iris
 * @date 2022/4/5
 */
public interface BusinessService {
    /**
     * 采购
     * @param userId
     * @param commodityCode
     * @param orderCount
     */
    public void purchase(String userId, String commodityCode, int orderCount);
}
