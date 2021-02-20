package com.taobao.csp.sentinel.dashboard.datasource.entity.mysql.entity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2019年1月3日
 */
@Table(name = "sys_user")
public class User {
    @Id
    private Long userId;
    private String userName;
    private Integer userAge;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
}
