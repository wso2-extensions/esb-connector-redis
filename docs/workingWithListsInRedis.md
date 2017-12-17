# Working with Lists in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with "lists". Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with "lists", see [  sample configuration  ](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [blPop](#blPop)    | Remove and get the first element in a list or block until one is available |
| [brPop](#brPop)    | Remove an get the element in a list or block one is available |
| [lInsert](#lInsert)    | Insert an element before or after another element in a list |
| [lLen](#lLen)    | Get a length of a list |
| [lPop](#lPop)    | Remove and get the first element in a list |
| [lPush](#lPush)    | Prepend one or multiple values to a list |
| [lPushX](#lPushX)    | Prepend a value of an element in a list by its index |
| [lRange](#lRange)    | Get a range of elements from a list |
| [lRem](#lRem)    | Remove element from a list |
| [lSet](#lSet)    | Set the value of an element in a list by it's index |
| [lTrim](#lTrim)    | Trim a list to the specified range |
| [rPopLPush](#rPopLPush)    | Remove the list element in a list, prepend it to another list and return it |
| [rPush](#rPush)    | Append one or more multiple values to a list |
| [rPushX](#rPushX)    | Append a value to a list, only if the list exists |

### Operation details
This section provides details on the operations.

#### BlPop
The blPop operation is used to remove and get the first element in a list or block until one is available.

**blPop**
```xml
<redis.blPop>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisBlPopTimeout>{$ctx:redisBlPopTimeout}</redisBlPopTimeout>
</redis.blPop>
```

**Properties**
* redisKey: The name of the key.
* redisBlPopTimeout: The time to be added for blocking list pop from the head.

**Sample request**

Following is a sample REST/JSON request that can be handled by the blPop operation.

```json
{
    "redisKey":"sampleKey",
    "redisBlPopTimeout":"0"
}
```

**Related redis documentation**

[https://redis.io/commands/blpop](https://redis.io/commands/blpop)

#### BrPop
The brPop operation is used to remove an get the element in a list or block one is available.

**brPop**
```xml
<redis.brPop>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisBrPopTimeout>{$ctx:redisBrPopTimeout}</redisBrPopTimeout>
</redis.brPop>
```

**Properties**
* redisKey: The name of the key.
* redisBrPopTimeout: The time to be added for blocking list pop from the tail.

**Sample request**

Following is a sample REST/JSON request that can be handled by the brPop operation.

```json
{
    "redisKey":"sampleKey",
    "redisBrPopTimeout":"0"
}
```

**Related redis documentation**

[https://redis.io/commands/brpop](https://redis.io/commands/brpop)

#### LInsert
The lInsert operation is used to insert an element before or after another element in a list.

**lInsert**
```xml
<redis.lInsert>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisWhere>{$ctx:redisWhere}</redisWhere>
    <redisPivot>{$ctx:redisPivot}</redisPivot>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.lInsert>
```

**Properties**
* redisKey: The name of the key.
* redisWhere: The place, value should be added.
* redisPivot: The value, use as a pivot element.
* redisValue: The value to be added to the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lInsert operation.

```json
{
    "redisKey":"sampleKey",
    "redisWhere":"BEFORE",
    "redisPivot":"samplePivotElement",
    "redisValue":"sampleInsertElement"
}
```

**Related redis documentation**

[https://redis.io/commands/linsert](https://redis.io/commands/linsert)

#### LLen
The lLen operation is used to get a length of a list.

**lLen**
```xml
<redis.lLen>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.lLen>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lLen operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/llen](https://redis.io/commands/llen)

#### LPop
The lPop operation is used to remove and get the first element in a list.

**lPop**
```xml
<redis.lPop>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.lPop>
```

**Properties**
* redisKey: The name of the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lPop operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/lpop](https://redis.io/commands/lpop)

#### LPush
The lPush operation is used to prepend one or multiple values to a list.

**lPush**
```xml
<redis.lPush>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStrings>{$ctx:redisStrings}</redisStrings>
</redis.lPush>
```

**Properties**
* redisKey: The name of the key.
* redisStrings: The values to be added to the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lPush operation.

```json
{
    "redisKey":"sampleKey",
    "redisStrings":"sampleValues"
}
```

**Related redis documentation**

[https://redis.io/commands/lpush](https://redis.io/commands/lpush)

#### LPushX
The lPushX operation is used to prepend a value of an element in a list by its index.

**lPushX**
```xml
<redis.lPushX>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisString>{$ctx:redisString}</redisString>
</redis.lPushx>
```

**Properties**
* redisKey: The name of the key.
* redisString: The values to be added to the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lPushx operation.

```json
{
    "redisKey":"sampleKey",
    "redisString":"sampleValue"
}
```

**Related redis documentation**

[https://redis.io/commands/lpushx](https://redis.io/commands/lpushx)

#### LRange
The lRange operation is used to get a range of elements from a list.

**lRange**
```xml
<redis.lRange>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStart>{$ctx:redisStart}</redisStart>
    <redisEnd>{$ctx:redisEnd}</redisEnd>
</redis.lRange>
```

**Properties**
* redisKey: The name of the key.
* redisStart: The starting index.
* redisEnd: the ending index.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lRange operation.

```json
{
    "redisKey":"sampleKey",
    "redisStart":"0",
    "redisEnd":"-1"
}
```

**Related redis documentation**

[https://redis.io/commands/lrange](https://redis.io/commands/lrange)

#### LRem
The lRem operation is used to remove element from a list.

**lRem**
```xml
<redis.lRem>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisCount>{$ctx:redisCount}</redisCount>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.lRem>
```

**Properties**
* redisKey: The name of the key.
* redisCount: The number of element to be remove.
* redisValue: the value of key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lRem operation.

```json
{
    "redisKey":"sampleKey",
    "redisStart":"0",
    "redisEnd":"-1"
}
```

**Related redis documentation**

[https://redis.io/commands/lrem](https://redis.io/commands/lrem)

#### LSet
The lSet operation is used to set the value of an element in a list by it's index.

**lSet**
```xml
<redis.lSet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisIndex>{$ctx:redisIndex}</redisIndex>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.lSet>
```

**Properties**
* redisKey: The name of the key.
* redisIndex: The starting index.
* redisValue: the value of key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lSet operation.

```json
{
    "redisKey":"sampleKey",
    "redisIndex":"0",
    "redisValue":"sampleValue"
}
```

**Related redis documentation**

[https://redis.io/commands/lset](https://redis.io/commands/lset)

#### LTrim
The lTrim operation is used to trim a list to the specified range.

**lTrim**
```xml
<redis.lTrim>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStart>{$ctx:redisStart}</redisStart>
    <redisEnd>{$ctx:redisEnd}</redisEnd>
</redis.lTrim>
```

**Properties**
* redisKey: The name of the key.
* redisStart: The starting index.
* redisEnd: the ending index.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lTrim operation.

```json
{
    "redisKey":"sampleKey",
    "redisStart":"0",
    "redisEnd":"-1"
}
```

**Related redis documentation**

[https://redis.io/commands/ltrim](https://redis.io/commands/ltrim)

#### RPopLPush
The rPopLPush operation is used to remove the list element in a list, prepend it to another list and return it.

**rPopLPush**
```xml
<redis.rPopLPush>
    <redisSrckey>{$ctx:redisSrckey}</redisSrckey>
    <redisDstkey>{$ctx:redisDstkey}</redisDstkey>
</redis.rPopLPush>
```

**Properties**
* redisSrckey: The value of source key.
* redisDstkey: The value of destination key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the rPopLPush operation.

```json
{
    "redisSrckey":"sampleSourceKey",
    "redisDstkey":"sampleDestinationKey"
}
```

**Related redis documentation**

[https://redis.io/commands/rpoplpush](https://redis.io/commands/rpoplpush)

#### RPush
The rPush operation is used to append one or more multiple values to a list.

**rPush**
```xml
<redis.rPush>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStrings>{$ctx:redisStrings}</redisStrings>
</redis.rPush>
```

**Properties**
* redisKey: The value of key.
* redisStrings: The values to be added to the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the rPush operation.

```json
{
    "redisKey":"sampleKey",
    "redisStrings":"sampleValues"
}
```

**Related redis documentation**

[https://redis.io/commands/rpush](https://redis.io/commands/rpush)

#### RPushX
The rPushX operation is used to append a value to a list, only if the list exists.

**rPushX**
```xml
<redis.rPushX>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.rPushX>
```

**Properties**
* redisKey: The value of key.
* redisValue: The value to be added to the key.

**Sample request**

Following is a sample REST/JSON request that can be handled by the rPushX operation.

```json
{
    "redisKey":"sampleKey",
    "redisValue":"sampleValue"
}
```

**Related redis documentation**

[https://redis.io/commands/rpushx](https://redis.io/commands/rpushx)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to Redis with the init operation and use the blPop operation. The sample request for this proxy can be found in [blPop sample request](#request). You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="blPop"
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
         <property expression="json-eval($.redisBlPopTimeout)" name="redisBlPopTimeout"/>
         <redis.init>
            <redisHost>{$ctx:redisHost}</redisHost>
            <redisPort>{$ctx:redisPort}</redisPort>
            <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
         </redis.init>
         <redis.blPop>
            <redisKey>{$ctx:redisKey}</redisKey>
            <redisBlPopTimeout>{$ctx:redisBlPopTimeout}</redisBlPopTimeout>
         </redis.blPop>
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>                               
```