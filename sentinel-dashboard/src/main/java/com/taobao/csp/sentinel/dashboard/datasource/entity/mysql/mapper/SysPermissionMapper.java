package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2019年1月3日
 */
@Mapper
public interface SysPermissionMapper {
    List<String> selectPermissionByUserId(@Param("userId") long userId);
}
