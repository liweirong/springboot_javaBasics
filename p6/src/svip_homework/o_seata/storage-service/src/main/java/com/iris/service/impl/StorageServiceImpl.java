package com.iris.service.impl;


import com.iris.entity.Storage;
import com.iris.mapper.StorageMapper;
import com.iris.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {


    @Autowired
    private StorageMapper storageMapper;

    /**
     * 扣除存储数量
     *
     * @param commodityCode
     * @param count
     */
    @Override
    public void deduct(String commodityCode, int count) {
        Storage storage1 = storageMapper.selectById(1);
        log.info("扣减库存commodityCode:{} 库存：{} count:{}", commodityCode, storage1.getCount(), count);
        storage1.setCount(storage1.getCount() - count);
        storageMapper.updateById(storage1);
    }
}