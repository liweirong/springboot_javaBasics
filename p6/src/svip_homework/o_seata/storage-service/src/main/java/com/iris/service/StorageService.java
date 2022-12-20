package com.iris.service;


public interface StorageService {

    /**
     * 扣除存储数量
     * @param commodityCode
     * @param count
     */
    void deduct(String commodityCode, int count);
}
