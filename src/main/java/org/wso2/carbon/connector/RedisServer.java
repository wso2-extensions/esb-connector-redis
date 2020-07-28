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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.MessageContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisServer {

    public Jedis connect(MessageContext messageContext) {
        Log log = LogFactory.getLog(this.getClass());
        int port = 0;
        int timeout = 0;
        boolean useSsl = false;
        String cacheKey = "";

        Jedis jedis = null;
        try {
            String host = messageContext.getProperty(RedisConstants.HOST).toString();
            if (messageContext.getProperty(RedisConstants.PORT) != ""
                    && messageContext.getProperty(RedisConstants.PORT) != null) {
                port = Integer.parseInt(messageContext.getProperty(RedisConstants.PORT).toString());
            }
            if (messageContext.getProperty(RedisConstants.TIMEOUT) != ""
                    && messageContext.getProperty(RedisConstants.TIMEOUT) != null) {
                timeout = Integer.parseInt(messageContext.getProperty(RedisConstants.TIMEOUT).toString());
            }
            if (messageContext.getProperty(RedisConstants.CACHEKEY) != ""
                    && messageContext.getProperty(RedisConstants.CACHEKEY) != null) {
                cacheKey = messageContext.getProperty(RedisConstants.CACHEKEY).toString();
            }
            if (messageContext.getProperty(RedisConstants.USESSL) != ""
                    && messageContext.getProperty(RedisConstants.USESSL) != null) {
                useSsl = Boolean.parseBoolean(messageContext.getProperty(RedisConstants.USESSL).toString());
            }

            if (port > 0 && cacheKey != "") {
                JedisShardInfo shardInfo = new JedisShardInfo(host, port, useSsl);
                shardInfo.setPassword(cacheKey);
                jedis = new Jedis(shardInfo);
            } else if (port > 0 && timeout > 0) {
                jedis = new Jedis(host, port, timeout);
                return jedis;
            } else if (port > 0) {
                jedis = new Jedis(host, port);
                return jedis;
            } else {
                jedis = new Jedis(host);
                return jedis;
            }
        } catch (Exception e) {
            log.error("Error while connecting to the server", e);
        }
        return jedis;
    }
}
