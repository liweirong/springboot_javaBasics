package com.iris.rpc;

import com.iris.rpc.annotion.MyRemoteService;

@MyRemoteService
public class OrderServiceImpl implements IOrderService {
    @Override
    public String queryOrderList() {
        return "I MISS HUIHUI";
    }
}
