/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel;

import java.util.concurrent.TimeUnit;

import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.log.RecordLog;

/**
 * Status checker thread for Sentinel.
 *
 * @author Eric Zhao
 * @since 0.2.0
 */
public class SentinelStatusChecker extends Thread {

    private volatile boolean active = true;

    private int period = 30000;
    private double contextWarnRatio = 0.8;
    private double resourceWarnRatio = 0.8;

    SentinelStatusChecker() {
        setName("sentinel-status-checker-thread");
        setDaemon(true);
    }

    @Override
    public void run() {
        while (active) {
            checkStatus();
            try {
                TimeUnit.MILLISECONDS.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        RecordLog.info("[SentinelStatusChecker] Checker thread stopped");
    }

    private void checkContextStatus() {
        int contextSize = ContextUtil.contextSize();
        if (contextSize >= Constants.MAX_CONTEXT_NAME_SIZE + 1) {
            RecordLog.warn("[SentinelStatusChecker] WARN: Amount of context exceeds the threshold "
                + Constants.MAX_CONTEXT_NAME_SIZE + ". Entries in new contexts will not take effect!");
        } else if (contextSize >= Constants.MAX_CONTEXT_NAME_SIZE * contextWarnRatio) {
            RecordLog.warn(String.format("[SentinelStatusChecker] WARN: Amount of context (%d) has risen up to more than %.2f%%"
                + " of the threshold (%d)", contextSize, contextWarnRatio * 100, Constants.MAX_CONTEXT_NAME_SIZE
            ));
        }
    }

    private void checkResourceStatus() {
        int size = CtSph.slotChainSize();
        int threshold = Constants.MAX_SLOT_CHAIN_SIZE;
        if (size >= threshold) {
            RecordLog.warn("[SentinelStatusChecker] WARN: Amount of resources exceeds the threshold "
                + threshold + ". Rules for new resource entries will not take effect!");
        } else if (size >= threshold * resourceWarnRatio) {
            RecordLog.warn(String.format("[SentinelStatusChecker] WARN: Amount of resources (%d) has risen up to more than %.2f%%"
                + " of the threshold (%d)", size, resourceWarnRatio * 100, threshold
            ));
        }
    }

    private void checkStatus() {
        checkResourceStatus();
        checkContextStatus();
    }

    public void stopTask() {
        this.active = false;
    }
}
