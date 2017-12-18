# Configuring Redis Operations

Download "jedis-2.1.0.jar" from [https://mvnrepository.com/artifact/redis.clients/jedis/2.1.0](https://mvnrepository.com/artifact/redis.clients/jedis/2.1.0) and put it into "{EI_Home}/lib".

Download Redis server from http://redis.io/download and start it.

To use the Redis connector, add the <redis.init> element in your configuration before carrying out any other redis operations.

**init**
```xml
<redis.init>
    <redisHost>{$ctx:redisHost}</redisHost>
    <redisPort>{$ctx:redisPort}</redisPort>
    <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
</redis.init>
```
**Properties** 
* redisHost: The server host (default localhost).
* redisPort: The port to server run (default 6379).
* redisTimeout: Time to live the server in milliseconds.


Now that you have connected to Redis, use the information in the following topics to perform various operations with the connector.

[Working with Connection in Redis](workingWithConnectionInRedis.md)

[Working with Hashes in Workday](workingWithHashesInRedis.md)

[Working with Keys in Workday](workingWithKeysInRedis.md)

[Working with Lists in Workday](workingWithListsInRedis.md)

[Working with Server in Workday](workingWithServerInRedis.md)

[Working with Sets in Workday](workingWithSetsInRedis.md)

[Working with Sorted Sets in Workday](workingWithSortedSetsInRedis.md)
