package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.mapper.SysPermissionMapper;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2019年1月3日
 */
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    public List<String> selectPermissionByUserId(long userId) {
        return sysPermissionMapper.selectPermissionByUserId(userId);
    }
}
