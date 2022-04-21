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

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.wso2.carbon.connector.util.RedisConstants;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static redis.clients.jedis.Protocol.DEFAULT_DATABASE;

public class RedisServer {

    private static JedisPool jedisPool = null;
    private static JedisSentinelPool jedisSentinelPool = null;
    private int maxConnections;
    private JedisCluster jedisCluster;

    private MessageContext messageContext;
    private Boolean isClusterEnabled = false;
    private int soTimeout = RedisConstants.DEFAULT_TIMEOUT;
    private int connectionTimeout;
    private boolean useSsl = false;
    private String cacheKey = null;
    private boolean isSentinelEnabled = false;
    private String masterName;
    private java.util.Set<String> sentinels;
    private String masterPassword;
    private int dbNumber = DEFAULT_DATABASE;
    private Lock lock = new ReentrantLock();
    private Lock jedisLock = new ReentrantLock();

    public RedisServer(MessageContext messageContext) {
        this.messageContext = messageContext;

        String redisClusterEnabled = (String) messageContext.getProperty(RedisConstants.REDIS_CLUSTER_ENABLED);
        if (redisClusterEnabled != null && !redisClusterEnabled.isEmpty()) {
            isClusterEnabled = Boolean.parseBoolean(redisClusterEnabled);
        }
        String soTimeoutProp = (String) messageContext.getProperty(RedisConstants.TIMEOUT);
        String connectionTimeoutProp = (String) messageContext.getProperty(RedisConstants.CONNECTION_TIMEOUT);
        String cacheKeyProp = (String) messageContext.getProperty(RedisConstants.CACHEKEY);
        String sslProp = (String) messageContext.getProperty(RedisConstants.USESSL);

        String sentinelEnabled = (String) messageContext.getProperty(RedisConstants.SENTINEL_ENABLED);
        String maxConnectionsProp = (String) messageContext.getProperty(RedisConstants.MAX_CONNECTIONS);

        if (maxConnectionsProp != null && !maxConnectionsProp.isEmpty()) {
            try {
                maxConnections = Integer.parseInt(maxConnectionsProp);
            } catch (NumberFormatException e) {
                throw new SynapseException(
                        "Invalid input for \"maxConnections\". Cannot parse " + maxConnectionsProp
                                + " to an Integer.", e);
            }
        } else {
            // setting 8 as the default value of maxConnections
            maxConnections = RedisConstants.DEFAULT_MAX_CONNECTIONS;
        }

        if (sentinelEnabled != null && !sentinelEnabled.isEmpty()) {
            isSentinelEnabled = Boolean.parseBoolean(sentinelEnabled);
        }

        if (soTimeoutProp != null && !soTimeoutProp.isEmpty()) {
            try {
                soTimeout = Integer.parseInt(soTimeoutProp);
            } catch (NumberFormatException e) {
                throw new SynapseException(
                        "Invalid input for \"redisTimeout\". Cannot parse " + soTimeoutProp + " to an Integer.", e);
            }
        }
        if (connectionTimeoutProp != null && !connectionTimeoutProp.isEmpty()) {
            try {
                connectionTimeout = Integer.parseInt(connectionTimeoutProp);
            } catch (NumberFormatException e) {
                throw new SynapseException(
                        "Invalid input for \"redisConnectionTimeout\". Cannot parse " + connectionTimeoutProp
                                + " to an Integer.", e);
            }
        } else {
            // setting socket timeout value as the default value of connection timeout
            connectionTimeout = soTimeout;
        }

        if (cacheKeyProp != null && !cacheKeyProp.isEmpty()) {
            cacheKey = cacheKeyProp;
        }

        if (sslProp != null && !sslProp.isEmpty()) {
            useSsl = Boolean.parseBoolean(sslProp);
        }
    }

    private void buildJedisPool(String host, int port, int connectionTimeout, int soTimeout,
                                boolean ssl) {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxConnections); //The maximum number of connections that are supported by the pool.
        poolConfig.setMaxIdle(maxConnections); // Is the actual maximum number of connections required by workloads
        // (maxTotal = maxIdle)
        poolConfig.setTestOnBorrow(false); //set to default false
        poolConfig.setTestOnReturn(false); //set to default false
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(poolConfig, host, port, connectionTimeout, soTimeout, null, dbNumber,
                null, ssl);
    }

    /**
     * Create a Jedis instance according to the parameters given.
     *
     * @return Jedis instance
     */
    private Jedis createJedis() {

        if (isSentinelEnabled) {
            return createSentinel();
        }
        
        String connectionURIProp = (String) messageContext.getProperty(RedisConstants.CONNECTION_URI);
        if (connectionURIProp != null) {
            try {
                URI connectionURI = new URI(connectionURIProp);
                JedisShardInfo shardInfo = new JedisShardInfo(connectionURI);
                shardInfo.setSoTimeout(soTimeout);
                shardInfo.setConnectionTimeout(connectionTimeout);
                return new Jedis(shardInfo);
            } catch (URISyntaxException e) {
                throw new SynapseException(
                        "Invalid input for \"redisConnectionURI\". Please provide a URI with valid syntax", e);
            }
        }

        String host = messageContext.getProperty(RedisConstants.HOST).toString();
        int port;
        int weight = RedisConstants.DEFAULT_WEIGHT;

        String portProp = (String) messageContext.getProperty(RedisConstants.PORT);
        if (portProp != null && !portProp.isEmpty()) {
            try {
                port = Integer.parseInt(portProp);
            } catch (NumberFormatException e) {
                throw new SynapseException(
                        "Invalid input for \"redisPort\". Cannot parse " + portProp + " to an Integer.", e);
            }
        } else {
            throw new SynapseException("Value for \"redisPort\" cannot be empty");
        }

        String weightProp = (String) messageContext.getProperty(RedisConstants.WEIGHT);
        if (weightProp != null && !weightProp.isEmpty()) {
            try {
                weight = Integer.parseInt(weightProp);
            } catch (NumberFormatException e) {
                throw new SynapseException(
                        "Invalid input for \"weight\". Cannot parse " + weightProp + " to an Integer.", e);
            }
        }

        if (!Objects.isNull(cacheKey) && !cacheKey.isEmpty()) {
            JedisShardInfo shardInfo = new JedisShardInfo(host, port, connectionTimeout, soTimeout, weight, useSsl);
            shardInfo.setPassword(cacheKey);
            return new Jedis(shardInfo);
        }
        //Use double lock to avoid creating a new Jedis Sentinel connection pool for each request.
        if (jedisPool == null) {
            jedisLock.lock();
            try {
                if (jedisPool == null) {
                    buildJedisPool(host, port, connectionTimeout, soTimeout, useSsl);
                }
            } finally {
                jedisLock.unlock();
            }
        }
        return jedisPool.getResource();
    }

    private Jedis createSentinel() {

        String masterNameProp = (String) messageContext.getProperty(RedisConstants.MASTER_NAME);
        if (masterNameProp != null && !masterNameProp.isEmpty()) {
            masterName = masterNameProp;
        } else{
            throw new SynapseException("Value for \"masterName\" cannot be empty in sentinel");
        }

        String sentinelsProp = (String) messageContext.getProperty(RedisConstants.SENTINELS);
        if (sentinelsProp != null && !sentinelsProp.isEmpty()) {
            sentinels = new HashSet<>(Arrays.asList(sentinelsProp.split(",")));
        } else {
            throw new SynapseException("Value for \"sentinels\" cannot be empty in sentinel");

        }

        String dbNumberProp = (String) messageContext.getProperty(RedisConstants.DB_NUMBER);
        if (dbNumberProp != null && !dbNumberProp.isEmpty()) {
            try {
                dbNumber = Integer.parseInt(dbNumberProp);
            } catch (NumberFormatException e) {
                throw new SynapseException(
                        "Invalid input for \"dbNumber\". Cannot parse " + dbNumberProp + " to an Integer.", e);
            }
        }

        String masterPasswordProp = (String) messageContext.getProperty(RedisConstants.MASTER_PASSWORD);
        if (masterPasswordProp != null && !masterPasswordProp.isEmpty()) {
            masterPassword = masterPasswordProp;
        }
        //Use double lock to avoid creating a new Jedis Sentinel connection pool for each request.
        if (jedisSentinelPool == null) {
            lock.lock();
            try {
                if (jedisSentinelPool == null) {
                    GenericObjectPoolConfig config = new GenericObjectPoolConfig();
                    config.setMaxTotal(maxConnections);
                    config.setMaxIdle(maxConnections);
                    jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, config, connectionTimeout,
                            soTimeout, masterPassword, dbNumber);
                }
            } finally {
                lock.unlock();
            }
        }
        return jedisSentinelPool.getResource();
    }

    /**
     * Create a JedisCluster instance according to the parameters given.
     *
     * @return JedisCluster instance
     */
    private JedisCluster createJedisCluster() {

        String clusterNodes;
        String clientName = null;
        int maxAttempts = RedisConstants.DEFAULT_MAX_ATTEMPTS;

        String clusterNodesProp = (String) messageContext.getProperty(RedisConstants.CLUSTER_NODES);
        String clientNameProp = (String) messageContext.getProperty(RedisConstants.CLIENT_NAME);
        String maxAttemptsProp = (String) messageContext.getProperty(RedisConstants.MAX_ATTEMPTS);

        if (clusterNodesProp != null && !clusterNodesProp.isEmpty()) {
            clusterNodes = clusterNodesProp;
        } else {
            throw new SynapseException("Redis cluster nodes cannot be empty");
        }
        if (clientNameProp != null && !clientNameProp.isEmpty()) {
            clientName = clientNameProp;
        }

        if (maxAttemptsProp != null && !maxAttemptsProp.isEmpty()) {
            try {
                maxAttempts = Integer.parseInt(maxAttemptsProp);
            } catch (NumberFormatException e) {
                throw new SynapseException(
                        "Invalid input for \"maxAttempts\". Cannot parse " + maxAttemptsProp + " to an Integer.", e);
            }
        }

        String[] redisNodes = clusterNodes.split(",");
        HashSet<HostAndPort> jedisClusterNodes = new HashSet<>();
        for (String nodeConfig : redisNodes) {
            String[] redisNode = nodeConfig.split(":");
            jedisClusterNodes.add(new HostAndPort(redisNode[0].trim(), Integer.parseInt(redisNode[1].trim())));
        }

        return new JedisCluster(jedisClusterNodes, connectionTimeout, soTimeout, maxAttempts,
                                cacheKey, clientName, new GenericObjectPoolConfig(),
                                useSsl);
    }

    public Jedis getJedis() {
        return createJedis();
    }

    public JedisCluster getJedisCluster() {
        this.jedisCluster = createJedisCluster();
        return jedisCluster;
    }

    /**
     * Close the datasources objects associated Jedis and JedisCluster instances.
     */
    public void close() {
        if (jedisCluster != null) {
            jedisCluster.close();
        }
    }

    public Boolean isClusterEnabled() {
        return isClusterEnabled;
    }
}
