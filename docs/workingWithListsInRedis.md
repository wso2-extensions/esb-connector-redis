# Working with Lists in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with lists. Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with lists, see [sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [blPop](#retrieving-the-first-element-in-a-list-or-blocking-until-an-element-is-available)    | Retrieves the first element, if available, or blocks the connection for a specific time until an element is available |
| [brPop](#retrieving-the-last-element-in-a-list-or-blocking-until-an-element-is-available)    | Retrieves the last element, if available, or blocks the connection for a specific time until an element is available |
| [lInsert](#inserting-an-element-to-a-list)    | Inserts an element before or after an existing element in a list |
| [lLen](#retrieving-the-length-of-a-list)    | Retrieves the length of a list |
| [lPop](#retrieving-the-first-element-in-a-list)    | Retrieves the first element in a list |
| [lPush](#inserting-one-or-more-elements-to-the-head-of-a-list)    | Inserts one or more elements to the head of a list |
| [lPushX](#inserting-one-or-more-elements-to-the-head-of-a-list-only-if-the-list-exists)    | Inserts one or more elements to the head of a list, only if the list already exists  |
| [lRange](#retrieving-a-range-of-elements-from-a-list)    | Retrieves a range of elements from a list |
| [lRem](#removing-elements-from-a-list)    | Removes elements from a list |
| [lSet](#setting-the-value-of-an-element-in-a-list-by-its-index)    | Sets the value of an element in a list by its index |
| [lTrim](#trimming-a-list-to-a-specified-range)    | Trims a list to a specified range |
| [rPopLPush](#retrieving-the-last-element-in-a-list)    | Retrieves the last element in a list |
| [rPush](#inserting-one-or-more-elements-to-the-tail-of-a-list)    | Inserts one or more elements to the tail of a list |
| [rPushX](#inserting-one-or-more-elements-to-the-tail-of-a-list-only-if-the-list-exists)    | Inserts one or more elements to the tail of a list, only if the list already exists |

### Operation details
This section provides more details on the operations.

#### Retrieving the first element in a list, or blocking until an element is available
The blPop operation retrieves the first element in a list, if available, or blocks the connection for a specified amount of time until an element is available.

**blPop**
```xml
<redis.blPop>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisBlPopTimeout>{$ctx:redisBlPopTimeout}</redisBlPopTimeout>
</redis.blPop>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisBlPopTimeout: The amount of time to keep the connection blocked, waiting for an element to be available in the head of the list.

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

#### Retrieving the last element in a list, or blocking until an element is available
The brPop operation retrieves the last element in a list, if available, or blocks the connection for a specified amount of time until an element is available.

**brPop**
```xml
<redis.brPop>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisBrPopTimeout>{$ctx:redisBrPopTimeout}</redisBrPopTimeout>
</redis.brPop>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisBlPopTimeout: The amount of time to keep the connection blocked, waiting for an element to be available in the tail of the list.


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

#### Inserting an element to a list
The lInsert operation inserts a specified element before or after an existing element in a list that is stored in a specified key.

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
* redisKey: The name of the key where the list is stored.
* redisWhere: The place where you want to add an element. Possible values are BEFORE or AFTER. For example, whether you want to add an element before a particular element that exists in the list.
* redisPivot: An existing element in the list that is used as the pivot element.
* redisValue: The element that you want to insert to the list.

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

#### Retrieving the length of a list
The lLen operation retrieves the length of a list that is stored in a specified key.

**lLen**
```xml
<redis.lLen>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.lLen>
```

**Properties**
* redisKey: The name of the key where the list is stored.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lLen operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/llen](https://redis.io/commands/llen)

#### Retrieving the first element in a list
The lPop operation retrieves the first element in a list that is stored in a specified key.

**lPop**
```xml
<redis.lPop>
    <redisKey>{$ctx:redisKey}</redisKey>
</redis.lPop>
```

**Properties**
* redisKey: The name of the key where the list is stored.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lPop operation.

```json
{
    "redisKey":"sampleKey"
}
```

**Related redis documentation**

[https://redis.io/commands/lpop](https://redis.io/commands/lpop)

#### Inserting one or more elements to the head of a list
The lPush operation inserts one or more elements to the head of a list that is stored in a specified key.

**lPush**
```xml
<redis.lPush>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStrings>{$ctx:redisStrings}</redisStrings>
</redis.lPush>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisStrings: One or more elements that you want to add to the head of the list.

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

#### Inserting one or more elements to the head of a list, only if the list already exists
The lPushX operation inserts one or more elements to the head of a list stored in a specified key, only if the key already exists and holds a list.

**lPushX**
```xml
<redis.lPushX>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisString>{$ctx:redisString}</redisString>
</redis.lPushx>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisString: One or more elements that you want to add to the head of the list.

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

#### Retrieving a range of elements from a list
The lRange operation retrieves a range of elements from a list.

**lRange**
```xml
<redis.lRange>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStart>{$ctx:redisStart}</redisStart>
    <redisEnd>{$ctx:redisEnd}</redisEnd>
</redis.lRange>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisStart: The starting index.
* redisEnd: The ending index.

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

#### Removing elements from a list
The lRem operation removes elements from a list.

**lRem**
```xml
<redis.lRem>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisCount>{$ctx:redisCount}</redisCount>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.lRem>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisCount: The number of occurrences of the element that you want to remove.
* redisValue: the element that you want to remove.

**Sample request**

Following is a sample REST/JSON request that can be handled by the lRem operation.

```json
{
"redisKey":"sampleKey",
"redisCount":"1",
"redisValue":"sampleValue"
}
```

**Related redis documentation**

[https://redis.io/commands/lrem](https://redis.io/commands/lrem)

#### Setting the value of an element in a list by its index
The lSet operation sets the value of an element in a list by its index.

**lSet**
```xml
<redis.lSet>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisIndex>{$ctx:redisIndex}</redisIndex>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.lSet>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisIndex: The starting index.
* redisValue: The value of key.

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

#### Trimming a list to a specified range
The lTrim operation trims a list to a specified range.

**lTrim**
```xml
<redis.lTrim>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStart>{$ctx:redisStart}</redisStart>
    <redisEnd>{$ctx:redisEnd}</redisEnd>
</redis.lTrim>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisStart: The starting index.
* redisEnd: The ending index.

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

#### Retrieving the last element in a list
The rPopLPush operation removes the last element in a list, then inserts it to another list, and then returns it.

**rPopLPush**
```xml
<redis.rPopLPush>
    <redisSrckey>{$ctx:redisSrckey}</redisSrckey>
    <redisDstkey>{$ctx:redisDstkey}</redisDstkey>
</redis.rPopLPush>
```

**Properties**
* redisSrckey: The name of the source key from where the last element is retrieved.
* redisDstkey: The name of destination key.

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

#### Inserting one or more elements to the tail of a list
The rPush operation inserts one or more elements to the tail of a list that is stored in a specified key.

**rPush**
```xml
<redis.rPush>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisStrings>{$ctx:redisStrings}</redisStrings>
</redis.rPush>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisStrings: One or more elements that you want to add to the tail of the list.

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

#### Inserting one or more elements to the tail of a list, only if the list already exists
The rPushX operation inserts one or more elements to the tail of a list stored in a specified key, only if the key already exists and holds a list.

**rPushX**
```xml
<redis.rPushX>
    <redisKey>{$ctx:redisKey}</redisKey>
    <redisValue>{$ctx:redisValue}</redisValue>
</redis.rPushX>
```

**Properties**
* redisKey: The name of the key where the list is stored.
* redisValue: One or more elements that you want to add to the tail of the list.

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
