package org.atguigu.cloud.service;

import org.atguigu.cloud.entities.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}
