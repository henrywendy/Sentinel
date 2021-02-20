package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.entity.SysUser;
import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.mapper.SysUserMapper;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2019年1月3日
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public SysUser findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}
