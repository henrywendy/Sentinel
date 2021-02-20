package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.entity.SysUser;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2019年1月3日
 */
@Mapper
public interface SysUserMapper {
    SysUser findByUserName(String userName);
}
