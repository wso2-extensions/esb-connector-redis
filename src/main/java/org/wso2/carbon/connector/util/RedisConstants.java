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

package org.wso2.carbon.connector.util;

public class RedisConstants {
    public static final String PORT = "redisPort";
    public static final String TIMEOUT = "redisTimeout";
    public static final String HOST = "redisHost";
    public static final String CONNECTION_URI = "redisConnectionURI";
    public static final String RESULT = "result";
    public static final String RESULT_OF_IDLE = "result_of_idle";
    public static final String KEY = "redisKey";
    public static final String VALUE = "redisValue";
    public static final String PATTERN = "redisPattern";
    public static final String OLDKEY = "redisOldKey";
    public static final String NEWKEY = "redisNewKey";
    public static final String SECONDS = "redisSeconds";
    public static final String UNIXTIME = "redisUnixTime";
    public static final String INTEGER = "redisInteger";
    public static final String START = "redisStart";
    public static final String END = "redisEnd";
    public static final String FIELD = "redisField";
    public static final String STRINGS = "redisStrings";
    public static final String COUNT = "redisCount";
    public static final String SRCKEY = "redisSrcKey";
    public static final String DSTKEY = "redisDstKey";
    public static final String MEMBERS = "redisMembers";
    public static final String MEMBER = "redisMember";
    public static final String SCORE = "redisScore";
    public static final String BLPOPTIMEOUT = "redisBlPopTimeout";
    public static final String BRPOPTIMEOUT = "redisBrPopTimeout";
    public static final String MIN = "redisMin";
    public static final String MAX = "redisMax";
    public static final String PARAMS = "redisParams";
    public static final String SETS = "redisSets";
    public static final String STRING = "redisString";
    public static final String WHERE = "redisWhere";
    public static final String PIVOT = "redisPivot";
    public static final String OFFSET = "redisOffset";
    public static final String INDEX = "redisIndex";
    public static final String KEYSVALUES = "redisKeysValues";
    public static final String FIELDSVALUES = "redisFieldsValues";
    public static final String FIELDS = "redisFields";
    public static final String MESSAGE = "redisMessage";
    public static final String CACHEKEY = "cacheKey";
    public static final String CACHEHOSTNAME = "cacheHostname";
    public static final String SENTINEL_USER = "sentinelUser";
    public static final String SENTINEL_CLIENT_NAME = "sentinelClientName";
    public static final String SENTINEL_PASSWORD = "sentinelPassword";
    public static final String SENTINEL_CONNECTION_TIMEOUT = "sentinelConnectionTimeout";
    public static final String SENTINEL_SO_TIMEOUT = "sentinelSoTimeout";
    public static final String REDIS_MASTER_USER = "masterUser";
    public static final String USESSL = "useSsl";
    public static final String CONNECTION_TIMEOUT = "redisConnectionTimeout";
    public static final String REDIS_CLUSTER_ENABLED = "redisClusterEnabled";
    public static final String CLUSTER_NODES = "clusterNodes";
    public static final String CLIENT_NAME = "clientName";
    public static final String MAX_ATTEMPTS = "maxAttempts";
    public static final String WEIGHT = "weight";
    public static final String MAX_CONNECTIONS = "maxConnections";
    public static final String IS_JMX_ENABLED = "jmxEnabled";
    public static final String CONNECTION_POOL_ID = "redisConnectionPoolId";
    public static final String INTERNAL_POOL_ID_SEPARATOR = "INTERNAL_POOL_ID_";
    public static final String ARTIFACT_NAME = "ARTIFACT_NAME";

    public static final int DEFAULT_TIMEOUT = 2000;
    public static final int DEFAULT_MAX_ATTEMPTS = 5;
    public static final int DEFAULT_MAX_CONNECTIONS = 8;
    public static final String DEFAULT_CONNECTION_POOL_ID = "0";
    public static final int DEFAULT_WEIGHT = 1;
    
     public static final String SENTINEL_ENABLED = "sentinelEnabled";
     public static final String MASTER_NAME = "masterName";
     public static final String SENTINELS = "sentinels";
     public static final String MASTER_PASSWORD = "masterPassword";
     public static final String DB_NUMBER = "dbNumber";
}
