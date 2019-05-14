/**
 * Copyright © 2015 eqxiu.com 北京中网易企秀科技有限公司  All rights reserved.
 */
package com.taobao.csp.sentinel.dashboard.repository.rule.ZkFlowRule;

import java.util.List;

import javax.annotation.Resource;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSONObject;
import com.taobao.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.taobao.csp.sentinel.dashboard.rule.DynamicRulePublisher;

/**
 * @description //TODO 设计说明
 * @author henry_chen
 * @date 2018年12月25日
 */
@Component("flowRuleZkPublisher")
public class FlowRuleZkPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {
    @Resource
    private ZkClientHelper zkClientHelper;

    private static String rolePath = "/sentinel_rules/rule_type";

    @Override
    public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        CuratorFramework zkClient = zkClientHelper.buildClient();
        Stat stat = zkClient.checkExists().forPath(rolePath + app);
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT);
        }
        zkClient.setData().forPath(rolePath + app, JSONObject.toJSONString(rules).getBytes());
    }

}
