package com.gupao.ssvip.rpc.client.controller;

import com.gupao.ssvip.rpc.GPReference;
import com.gupao.ssvip.rpc.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SevenClassController {

    @GPReference
    private IOrderService iOrderService;

    @GetMapping("/test")
    public String test(){
        return iOrderService.queryOrderList();
    }
}
