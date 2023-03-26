package com.qrrk.repair2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qrrk.repair2.entity.RepairMan;
import com.qrrk.repair2.mapper.RepairManMapper;
import org.springframework.stereotype.Service;

@Service
public class RepairManServiceImpl extends ServiceImpl<RepairManMapper, RepairMan>implements RepairManService {
}
