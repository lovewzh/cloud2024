package org.atguigu.cloud.controller;

import org.atguigu.cloud.entities.Order;
import jakarta.annotation.Resource;
import org.atguigu.cloud.resp.ResultData;
import org.atguigu.cloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public ResultData create(Order order)
    {
        orderService.create(order);
        return ResultData.success(order);
    }
}
