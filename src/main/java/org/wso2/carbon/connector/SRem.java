/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.connector;

import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class SRem extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {
        Jedis jedis = null;
        try {
            RedisServer serverObj = new RedisServer();
            jedis = serverObj.connect(messageContext);
            if (jedis != null) {
                String key = messageContext.getProperty(RedisConstants.KEY).toString();
                String members = messageContext.getProperty(RedisConstants.MEMBERS).toString();
                String[] keyValue = members.split(" ");
                Long response = jedis.srem(key, keyValue);
                if (response != null) {
                    messageContext.setProperty(RedisConstants.RESULT, response);
                } else {
                    handleException("Redis server throw null response", messageContext);
                }
            }
        } catch (Exception e) {
            handleException("Error while connecting the server or calling the redis method", e, messageContext);
        } finally {
            if (jedis != null) {
                jedis.disconnect();
            }
        }
    }
}
