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

package org.wso2.carbon.connector.operations;

import org.apache.synapse.MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.carbon.connector.util.Constants;
import org.wso2.carbon.connector.util.RedisConstants;
import org.wso2.carbon.connector.util.RedisUtils;
import redis.clients.jedis.Jedis;

public class HGet extends AbstractConnector {

    @Override
    public void connect(MessageContext messageContext) throws ConnectException {
        Jedis jedis = null;
        try {
            RedisServer serverObj = new RedisServer();
            jedis = serverObj.connect(messageContext);
            if (jedis != null) {
                String key = messageContext.getProperty(RedisConstants.KEY).toString();
                String field = messageContext.getProperty(RedisConstants.FIELD).toString();
                String response = jedis.hget(key, field);

                if (RedisUtils.isJsonPayload(response)) {
                    response = RedisUtils.escapeCharsJSON(response);
                    messageContext.setProperty(Constants.REDIS_MESSAGE_TYPE_PROPERTY,
                            Constants.REDIS_MESSAGE_TYPE_JSON);
                } else {
                    messageContext.setProperty(Constants.REDIS_MESSAGE_TYPE_PROPERTY,
                            Constants.REDIS_MESSAGE_TYPE_TEXT);
                }

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
