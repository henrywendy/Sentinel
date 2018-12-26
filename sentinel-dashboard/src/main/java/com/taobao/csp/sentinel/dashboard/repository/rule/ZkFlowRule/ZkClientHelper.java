/**
 * Copyright © 2015 eqxiu.com 北京中网易企秀科技有限公司  All rights reserved.
 */
package com.taobao.csp.sentinel.dashboard.repository.rule.ZkFlowRule;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2018年12月25日
 */
@Component
public class ZkClientHelper {

    @Value("zk.address")
    private String connectAddr;

    @Value("zk.timeout")
    private String sessionTimeOut;

    private String retryTime = "10";//默认重试10次

    private String retryTimeGap = "1000";//毫秒

    public CuratorFramework buildClient() {
        RetryPolicy policy = new ExponentialBackoffRetry(Integer.valueOf(this.retryTimeGap), Integer.valueOf(this.retryTime));
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(this.connectAddr).sessionTimeoutMs(Integer.valueOf(this.sessionTimeOut)).retryPolicy(policy).build();
        client.start();
        return client;
    }
}
