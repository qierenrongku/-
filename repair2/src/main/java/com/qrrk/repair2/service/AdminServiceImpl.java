package com.qrrk.repair2.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qrrk.repair2.entity.Admin;
import com.qrrk.repair2.mapper.AdminMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
