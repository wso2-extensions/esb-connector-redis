# Working with Sorted Sets in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with sorted sets. Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with sorted sets, see [sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [zadd](#adding-one-or-more-members-to-a-sorted-set)    | Adds one or more specified members to a sorted set, or updates the score if a specified member already exists |
| [zCount](#retrieving-a-count-of-members-in-a-sorted-set)    | Retrieves a count of members in a sorted set, with scores within the specified values |

### Operation details
This section provides more details on the operations.

#### Adding one or more members to a sorted set
The zadd operation adds one or more members to a sorted set, or update its score if a specified member already exists.

**zadd**
```xml
<redis.zadd>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisScore>{$ctx:redisScore}</redisScore>
    <redisMember>{$ctx:redisMember}</redisMember>
</redis.zadd>
```

**Properties**
* redisKey: The name of the key where the sorted set is stored.
* redisScore: The score of the sorted set.
* redisMember: The name of the member you want to add.

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

#### Retrieving a count of members in a sorted set
The zCount operation retrieves a count of members in a sorted set, with scores that are within the min and max values specified.

**zCount**
```xml
<redis.zCount>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisMin>{$ctx:redisMin}</redisMin>
    <redisMax>{$ctx:redisMax}</redisMax>
</redis.zCount>
```

**Properties**
* redisKey: The name of the key where the sorted set is stored.
* redisMin: The minimum score value.
* redisMax: The maximum score value.

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
Following is a sample proxy service that illustrates how to connect to Redis with the init operation, and then use the zadd operation. The sample request for this proxy can be found in [zadd sample request](#request). You can use this sample as a template for using other operations in this category.

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
