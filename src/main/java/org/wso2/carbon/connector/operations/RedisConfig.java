/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.connector.operations;

import org.apache.synapse.ManagedLifecycle;
import org.apache.synapse.MessageContext;
import org.apache.synapse.core.SynapseEnvironment;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.connector.util.RedisConstants;
import org.wso2.carbon.connector.util.RedisUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class RedisConfig extends AbstractConnector implements ManagedLifecycle {

    private static final ConcurrentHashMap<String, RedisServer> redisServers = new ConcurrentHashMap<>();
    private static final ReentrantLock lock = new ReentrantLock();
    @Override
    public void init(SynapseEnvironment synapseEnvironment) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {
        String name = getUniquePoolId(messageContext);
        RedisServer redisServer = redisServers.get(name);
        if (redisServer == null) {
            lock.lock();
            try {
                redisServer = redisServers.get(name);
                if (redisServer == null) {
                    redisServer = new RedisServer(messageContext);
                    redisServers.put(name, redisServer);
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static RedisServer getRedisServerInstance(MessageContext messageContext) {
        return redisServers.get(getUniquePoolId(messageContext));
    }

    public static String getUniquePoolId(MessageContext messageContext) {
        String poolIdProp = (String) messageContext.getProperty(RedisConstants.CONNECTION_POOL_ID);
        String artifactNameProp = (String) messageContext.getProperty(RedisConstants.ARTIFACT_NAME);
        String connectionName = (String) messageContext.getProperty(RedisConstants.CONNECTION_NAME);
        String uniquePoolId;
        if (connectionName != null && !connectionName.isEmpty()) {
            uniquePoolId = RedisUtils.getTenantSpecificConnectionName(connectionName, messageContext);
        } else if (poolIdProp != null && !poolIdProp.isEmpty()) {
            if (artifactNameProp != null) {
                uniquePoolId = artifactNameProp + RedisConstants.INTERNAL_POOL_ID_SEPARATOR + poolIdProp;
            } else {
                uniquePoolId = RedisConstants.INTERNAL_POOL_ID_SEPARATOR + poolIdProp;
            }
        } else {
            if (artifactNameProp != null) {
                uniquePoolId = artifactNameProp + RedisConstants.INTERNAL_POOL_ID_SEPARATOR
                        + RedisConstants.DEFAULT_CONNECTION_POOL_ID;
            } else {
                uniquePoolId = RedisConstants.INTERNAL_POOL_ID_SEPARATOR + RedisConstants.DEFAULT_CONNECTION_POOL_ID;
            }
        }
        return uniquePoolId;
    }
}
