package com.qrrk.repair2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qrrk.repair2.entity.Order;
import com.qrrk.repair2.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService  {
}
