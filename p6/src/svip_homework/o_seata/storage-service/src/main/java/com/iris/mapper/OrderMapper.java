package com.iris.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iris.entity.Order;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
