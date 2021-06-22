# Configuring Redis Operations

## Download and set up Redis

1. Download the jedis-2.10.2.jar file from [https://mvnrepository.com/artifact/redis.clients/jedis/2.10.2](https://mvnrepository.com/artifact/redis.clients/jedis/2.1.0) and copy it to the <EI_Home>/lib directory.

2. Download the Redis server from http://redis.io/download, and start it.

To use the Redis connector, add the <redis.init> element in your configuration before carrying out any other redis operations.

### init

**Standalone mode**
```xml
<redis.init>
    <redisHost>{$ctx:redisHost}</redisHost>
    <redisPort>{$ctx:redisPort}</redisPort>
    <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
    <redisConnectionTimeout>{$ctx:redisConnectionTimeout}</redisConnectionTimeout>
    <!-- to connect using a cache key, use the following two properties -->
    <cacheKey>{$ctx:cacheKey}</cacheKey>
    <useSsl>{$ctx:useSsl}</useSsl>
</redis.init>
```

If you prefer to use the connectionURI over above configuration, use the following init configuration.

```xml
<redis.init>
    <redisConnectionURI>{$ctx:redisConnectionURI}</redisConnectionURI>
    <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
    <redisConnectionTimeout>{$ctx:redisConnectionTimeout}</redisConnectionTimeout>
</redis.init>
```

**Cluster mode**
```xml
<redis.init>
    <redisClusterEnabled>true</redisClusterEnabled>   
    <clusterNodes>{$ctx:clusterNodes}</clusterNodes>
    <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
    <redisConnectionTimeout>{$ctx:redisConnectionTimeout}</redisConnectionTimeout>
    <maxAttempts>{$ctx:maxAttempts}</maxAttempts>
    <clientName>{$ctx:clientName}</clientName>
    <!-- to connect using a cache key, use the following two properties -->
    <cacheKey>{$ctx:cacheKey}</cacheKey>
    <useSsl>{$ctx:useSsl}</useSsl>
</redis.init>
```

**Properties** 
* redisClusterEnabled: A flag to enable the redis cluster mode (Default is false).
* clusterNodes: Comma separated list of the cluster nodes as Node1_hostname:Port,Node2_hostname:Port, etc.
* redisHost: The Redis host name (default localhost).
* redisPort: The port on which the Redis server is running (The default port is 6379).
* redisTimeout: The server TTL (Time to live) in milliseconds (The default is 2000ms).
* redisConnectionTimeout: The connection TTL (Time to live) in milliseconds (The default equals to the server TT).
* cacheKey: Key of the cache (password).
* useSsl: A flag to switch between SSL and non-SSL. Default is false.
* clientName: Name of the Redis client.
* maxAttempts: The number of retries.
* redisConnectionURI: The Redis connection URI in the form of `redis://[user:password@]host[:port]/[database]` or 
  `rediss://[user:password@]host[:port]/[database]` to connect over TLS/SSL


Now that you have connected to Redis, use the information in the following topics to perform various operations with the connector:

[Working with Connection Commands in Redis](workingWithConnectionInRedis.md)

[Working with Hashes in Redis](workingWithHashesInRedis.md)

[Working with Keys in Redis](workingWithKeysInRedis.md)

[Working with Lists in Redis](workingWithListsInRedis.md)

[Working with Server Commands in Redis](workingWithServerInRedis.md)

[Working with Sets in Redis](workingWithSetsInRedis.md)

[Working with Sorted Sets in Redis](workingWithSortedSetsInRedis.md)
