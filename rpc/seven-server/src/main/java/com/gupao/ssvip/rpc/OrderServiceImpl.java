package com.gupao.ssvip.rpc;

import com.gupao.ssvip.rpc.annotion.GpRemoteService;

@GpRemoteService
public class OrderServiceImpl implements IOrderService {
    @Override
    public String queryOrderList() {
        return "I MISS HUIHUI";
    }
}
