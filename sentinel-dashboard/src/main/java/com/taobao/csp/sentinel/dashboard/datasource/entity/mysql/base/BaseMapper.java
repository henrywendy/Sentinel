package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @description 自己的Mapper基类
 * @author henry_chen
 * @date 2019年1月3日
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T>{
}
