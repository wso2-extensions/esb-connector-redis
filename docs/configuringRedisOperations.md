# Configuring Redis Operations

### Download and set up Redis

1. Download the jedis-2.10.2.jar file from [https://mvnrepository.com/artifact/redis.clients/jedis/2.10.2](https://mvnrepository.com/artifact/redis.clients/jedis/2.1.0) and copy it to the <EI_Home>/lib directory.

2. Download the Redis server from http://redis.io/download, and start it.

To use the Redis connector, add the <redis.init> element in your configuration before carrying out any other redis operations.

**init**
```xml
<redis.init>
    <redisHost>{$ctx:redisHost}</redisHost>
    <redisPort>{$ctx:redisPort}</redisPort>
    <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
</redis.init>
```
If you are connecting using a cache key, use the following init configuration.

```xml
<redis.init>
    <redisHost>{$ctx:redisHost}</redisHost>
    <redisPort>{$ctx:redisPort}</redisPort>
    <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
    <cacheKey>{$ctx:cacheKey}</cacheKey>
    <useSsl>{$ctx:useSsl}</useSsl>
</redis.init>
```

**Properties** 
* redisHost: The Redis host name (default localhost).
* redisPort: The port on which the Redis server is running (The default port is 6379).
* redisTimeout: The server TTL (Time to live) in milliseconds.


Now that you have connected to Redis, use the information in the following topics to perform various operations with the connector:

[Working with Connection Commands in Redis](workingWithConnectionInRedis.md)

[Working with Hashes in Redis](workingWithHashesInRedis.md)

[Working with Keys in Redis](workingWithKeysInRedis.md)

[Working with Lists in Redis](workingWithListsInRedis.md)

[Working with Server Commands in Redis](workingWithServerInRedis.md)

[Working with Sets in Redis](workingWithSetsInRedis.md)

[Working with Sorted Sets in Redis](workingWithSortedSetsInRedis.md)
