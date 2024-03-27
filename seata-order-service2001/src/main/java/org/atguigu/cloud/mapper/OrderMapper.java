package org.atguigu.cloud.mapper;

import org.atguigu.cloud.entities.Order;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service
public interface OrderMapper extends Mapper<Order> {
}