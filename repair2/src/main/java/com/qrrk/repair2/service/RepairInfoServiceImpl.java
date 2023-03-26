package com.qrrk.repair2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qrrk.repair2.entity.RepairInfo;
import com.qrrk.repair2.mapper.RepairInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class RepairInfoServiceImpl extends ServiceImpl<RepairInfoMapper, RepairInfo> implements RepairInfoService{
}
