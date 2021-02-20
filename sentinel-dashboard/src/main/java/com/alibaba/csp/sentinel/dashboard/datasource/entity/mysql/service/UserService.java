package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.entity.User;
import com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.mapper.UserMapper;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 15:01
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Page<User> getUsers() {
        return userMapper.getUsers();
    }

    public User selectById(long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
