package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.Page;
import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.base.BaseMapper;
import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.entity.User;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2019年1月3日
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    Page<User> getUsers();
}
