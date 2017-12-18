# Working with Sorted Sets in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with "sorted sets". Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with "sorted sets", see [  sample configuration  ](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [zadd](#zadd)    | Add one or more members to a sorted set or update its score if it already exist |
| [zCount](#zCount)    | Count the members in a sorted set with scores within the given values |

### Operation details
This section provides details on the operations.

#### Zadd
The zadd operation is used to add one or more members to a sorted set or update its score if it already exist.

**zadd**
```xml
<redis.zadd>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisScore>{$ctx:redisScore}</redisScore>
    <redisMember>{$ctx:redisMember}</redisMember>
</redis.zadd>
```

**Properties**
* redisKey: The name of the key.
* redisScore: The score value.
* redisMember: The name of the member.

**Sample request**

Following is a sample REST/JSON request that can be handled by the zadd operation.

```json
{
    "redisKey":"sampleKey",
    "redisScore":"1.1",
    "redisMember":"sampleMember"
}
```

**Related redis documentation**

[https://redis.io/commands/zadd](https://redis.io/commands/zadd)

#### ZCount
The zCount operation is used to count the members in a sorted set with scores within the given values.

**zCount**
```xml
<redis.zCount>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisMin>{$ctx:redisMin}</redisMin>
    <redisMax>{$ctx:redisMax}</redisMax>
</redis.zCount>
```

**Properties**
* redisKey: The name of the key.
* redisMin: The minimum value.
* redisMax: The maximum value.

**Sample request**

Following is a sample REST/JSON request that can be handled by the zCount operation.

```json
{
    "redisKey":"sampleKey",
    "redisMin":"1.1",
    "redisMax":"2.2"
}
```

**Related redis documentation**

[https://redis.io/commands/zcount](https://redis.io/commands/zcount)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to Redis with the init operation and use the zadd operation. The sample request for this proxy can be found in [zadd sample request](#request). You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="zadd"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.redisHost)" name="redisHost"/>
         <property expression="json-eval($.redisPort)" name="redisPort"/>
         <property expression="json-eval($.redisTimeout)" name="redisTimeout"/>
         <property expression="json-eval($.redisKey)" name="redisKey"/>
         <property expression="json-eval($.redisScore)" name="redisScore"/>
         <property expression="json-eval($.redisMember)" name="redisMember"/>
         <redis.init>
            <redisHost>{$ctx:redisHost}</redisHost>
            <redisPort>{$ctx:redisPort}</redisPort>
            <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
         </redis.init>
         <redis.zadd>
            <redisKey>{$ctx:redisKey}</redisKey>
            <redisScore>{$ctx:redisScore}</redisScore>
            <redisMember>{$ctx:redisMember}</redisMember>
         </redis.zadd>
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>                                
```